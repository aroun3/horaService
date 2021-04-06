--- NOMENCLATURE ---
-- NON_DISPONIBLE = 0
-- EARLY = 1
-- ONTIME = 2
-- LATE=3
-- ================ Fonction pour obtenir le status arrival ==============
DROP FUNCTION if exists public."log_transaction"();

create or replace function public."log_transaction"() returns setof varchar
	language 'plpgsql'
as $$ 
declare
	history_record RECORD;
	tmp_row_record RECORD;
    logDateParam RECORD;
	callbackDate timestamp;
	daySelect timestamp;

	_arrival_time time;
	_arrival_id Integer;
	_arrival_terminal_id Integer;
	
	_departure_time time;
	_departure_id Integer;
	_departure_terminal_id Integer;
	
	_presence_periode time;
	
	_arrival_state text;
	_departure_state text;
	_presence_state text;
	_is_absent boolean;
	
	_count_inlog Integer;
	
	res varchar;
	
begin

	select hld.curr_date as currentDate, hld.nextdate as nextDate into logDateParam from h_log_dateparam hld  LIMIT 1;
	
	if found then
		if logDateParam.nextDate::date is NULL then
			res := 'La date callback est vide';
		else
			daySelect := logDateParam.nextDate::date;
			
			-- creation de table temporaire
			CREATE TEMPORARY TABLE tmp_log_transaction (
				id serial NOT NULL,
				emp_code character varying(20)  NOT NULL,
				arrival_time time with time zone,
				arrival_id smallint,
				arrival_terminal_id integer,
				departure_time time with time zone,
				departure_id smallint,
				departure_terminal_id integer,
				presence_periode time,
				arrival_state text,
				departure_state character varying(2),
				presence_state character varying(10),
				is_absent boolean,
				log_date timestamp,
				CONSTRAINT tmp_log_transaction_pkey PRIMARY KEY (id)
			) ON COMMIT DROP;
			
			-- =============== TEST VALUE ===========
			daySelect = '2021-02-18'::date;
			-- ======================================
			
			for tmp_row_record in SELECT * FROM iclock_transaction it WHERE it.punch_time::date = daySelect::date --logDateParam.nextDate::date

			loop
		
				SELECT COUNT(*) INTO _count_inlog FROM h_log_transaction hlt WHERE hlt.emp_code = tmp_row_record.emp_code AND hlt.log_date = daySelect::date;
				
				if _count_inlog = 0 then -- Pour eviter d'ajouter deux fois 
					-- le status de arrival (NON_DISPONIBE = 0, EARLY = 1, ONTIME = 2, LATE = 3)
					SELECT gapr.id, gapr.arrival_time::time, gapr.arrival_state, gapr.arrival_terminal_id into _arrival_id,_arrival_time,_arrival_state,_arrival_terminal_id FROM public."getArrivalPunchRecord"(tmp_row_record.emp_code::text,daySelect::date) gapr(id Integer,arrival_time time,arrival_state text,arrival_terminal_id Integer);

					-- le status de depart (NON_DISPONIBE = 0, EARLY = 1, ONTIME = 2, LATE = 3)
					SELECT gdpr.id, gdpr.departure_time::time,gdpr.departure_state,gdpr.departure_terminal_id into _departure_id,_departure_time,_departure_state,_departure_terminal_id FROM public."getDeparturePunchRecord"(tmp_row_record.emp_code::text,daySelect::date) gdpr(id Integer, departure_time time,departure_state text, departure_terminal_id Integer);

					-- Diffrence de temps : On ne peut calculer le temps que si departure_time et arrival_time sont non nul
					if _departure_time::time <> NULL AND _arrival_time::time <> NULL then
						_presence_periode = _arrival_time::time - _departure_time::time;
					else
						_presence_periode = '00:00:00';
					end if;

					-- Status de la journée en fonction du nombre d'heure
					SELECT INTO _presence_state 
					CASE 
						WHEN _presence_periode::time < '08:00:00'::time THEN 'MOINS'
						WHEN _presence_periode::time > '08:00:00'::time THEN 'PLUS'
						ELSE 'NORMAL'
					END;

					-- On prend en compte seulement les vrais absent
					SELECT INTO _is_absent
					CASE 
						WHEN _arrival_state <> '0' OR _departure_state <> '0' THEN false
						ELSE true
					END;

					raise notice 'emp_code: % - arrival_time : % - arrival_id : % - departure_time : % - departure_id : % - presence_periode : % - arrival_state : % - departure_state : % -presence_periode: % - is_absent : %',
					tmp_row_record.emp_code,_arrival_time,_arrival_id,_departure_time,_departure_id,_presence_periode,_arrival_state,_departure_state, _presence_state,_is_absent;


					INSERT INTO tmp_log_transaction(
						emp_code,
						arrival_time,
						arrival_id,
						arrival_terminal_id,
						departure_time,
						departure_id,
						departure_terminal_id,
						presence_periode,
						arrival_state,
						departure_state,
						presence_state,
						is_absent,
						log_date
					) VALUES(
						tmp_row_record.emp_code,
						_arrival_time,
						_arrival_id,
						_arrival_terminal_id,
						_departure_time,
						_departure_id,
						_departure_terminal_id,
						_presence_periode,
						_arrival_state,
						_departure_state,
						_presence_state,
						_is_absent,
						daySelect::date
					);
				end if;
				
			end loop;

			-- Insertion dans la base
			INSERT INTO h_log_transaction(
					emp_code,
					arrival_time,
					arrival_id,
					arrival_terminal_id,
					departure_time,
					departure_id,
					departure_terminal_id,
					presence_periode,
					arrival_state,
					departure_state,
					presence_state,
					is_absent,
					log_date) 
					
					(SELECT 
						emp_code,
						arrival_time,
						arrival_id,
						arrival_terminal_id,
						departure_time,
						departure_id,
					 	departure_terminal_id,
						presence_periode,
						arrival_state,
						departure_state,
						presence_state,
						is_absent,
					 	log_date
					 	FROM tmp_log_transaction);
			res := 'OK';
		end if;
	else
		res := 'NOT FOUND';
	end if;
	
	-- mise a jour de la date suivante
	if res = 'OK' then
		UPDATE h_log_dateparam SET curr_date = daySelect::date, nextdate = daySelect::date + integer '1';
	end if;
	
    EXCEPTION WHEN unique_violation THEN
        		GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;
				
	return next res;
	
end
$$;