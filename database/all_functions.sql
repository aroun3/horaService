
-- ==================== getArrivalPunchRecord ==================
DROP FUNCTION IF EXISTS public."getArrivalPunchRecord"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getArrivalPunchRecord"(empCode text, dateSelected date) 
returns SETOF record
	language 'plpgsql'
AS $$ 
DECLARE
    _result RECORD;
    rec RECORD;
BEGIN
    IF empCode <> '' AND empCode IS NOT NULL THEN

        SELECT * INTO rec FROM h_params LIMIT 1;

        SELECT INTO _result
            it.id as id,
        	it.punch_time::time without time zone as arrival_time,
			CASE
				WHEN it.punch_time::time without time zone >= rec.min_checkin::time AND it.punch_time::time without time zone < rec.h_checkin::time THEN '1'::text
				WHEN it.punch_time::time without time zone >= rec.h_checkin::time AND it.punch_time::time without time zone < rec.late_checkin::time THEN '2'::text
				WHEN it.punch_time::time without time zone >= rec.late_checkin::time AND it.punch_time::time without time zone < rec.max_checkin::time  THEN '3'::text
				ELSE '0'::text
			END as arrival_state,
			it.terminal_id as arrival_terminal_id

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        ORDER BY it.punch_time ASC LIMIT 1;

		IF _result.arrival_state IS NULL THEN
			_result.arrival_state = '0';
		END IF;
    END IF;
	
	return NEXT _result;
END
$$;

-- ===================== getDeparturePunchRecord ================

DROP FUNCTION IF EXISTS public."getDeparturePunchRecord"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getDeparturePunchRecord"(empCode text, dateSelected date) 
returns SETOF RECORD
	language 'plpgsql'
AS $$ 
DECLARE
    _result RECORD;
	rec RECORD;
BEGIN
    IF empCode <> '' AND empCode IS NOT NULL THEN

        SELECT * INTO rec FROM h_params LIMIT 1;

        SELECT INTO _result
        it.id as id,
        it.punch_time::time without time zone as departure_time,
		CASE
            WHEN it.punch_time::time without time zone >= rec.min_checkout::time AND it.punch_time::time without time zone < rec.early_checkout::time THEN '1'::text
            WHEN it.punch_time::time without time zone >= rec.early_checkout::time AND it.punch_time::time without time zone < rec.h_checkout::time THEN '2'::text
            WHEN it.punch_time::time without time zone >= rec.h_checkout::time AND it.punch_time::time without time zone < rec.max_checkout::time  THEN '3'::text
            ELSE '0'::text
        END as departure_state,
		it.terminal_id as departure_terminal_id
		
        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkout::time AND 
		it.punch_time::time <= rec.max_checkout::time 
        ORDER BY it.punch_time ASC LIMIT 1;

		IF _result.departure_state IS NULL THEN
			_result.departure_state = '0';
		END IF;
    END IF;
	
    RETURN NEXT _result;
END
$$;

--- ==================== getEmployeeArea ====================

DROP FUNCTION IF EXISTS public."getEmployeeArea"(empId Integer) CASCADE;

CREATE OR replace FUNCTION public."getEmployeeArea"(empId Integer) returns setof varchar
	language 'plpgsql'
AS $$ 
DECLARE
    res varchar;
BEGIN
    IF empId > 0 AND empId IS NOT NULL THEN
        SELECT string_agg(pa.area_name::text, ','::text) AS string_agg INTO res
        FROM personnel_area pa 
        WHERE pa.id IN (SELECT pea.area_id FROM personnel_employee_area as pea WHERE pea.employee_id = empId);
    END IF;

    RETURN NEXT res;
END
$$;

--- EXCUTION DE LA VUE EMPLOYEE ==========


-- ================== log_transaction FUNCTION POUR LE SCRIPT TACHE PLANIFIER ===============================
DROP FUNCTION if exists public."log_transaction"() CASCADE;

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
			daySelect = '2021-04-05'::date;
			-- ======================================
			
			for tmp_row_record in SELECT * FROM employee_view

			loop
				-- Pour eviter les doublons pour une journée
				SELECT COUNT(*) INTO _count_inlog FROM h_log_transaction hlt WHERE hlt.emp_code = tmp_row_record.emp_code AND hlt.log_date = daySelect::date;
				
				if _count_inlog = 0 then
					-- le status de arrival (NON_DISPONIBE = 0, EARLY = 1, ONTIME = 2, LATE = 3)
					SELECT gapr.id, 
					gapr.arrival_time::time, 
					gapr.arrival_state, 
					gapr.arrival_terminal_id into 
					_arrival_id,
					_arrival_time,
					_arrival_state,
					_arrival_terminal_id 
					FROM public."getArrivalPunchRecord"(tmp_row_record.emp_code::text,daySelect::date) gapr(id Integer,arrival_time time,arrival_state text,arrival_terminal_id Integer);

					-- le status de depart (NON_DISPONIBE = 0, EARLY = 1, ONTIME = 2, LATE = 3)
					SELECT gdpr.id, 
					gdpr.departure_time::time,
					gdpr.departure_state,
					gdpr.departure_terminal_id into 
					_departure_id,
					_departure_time,
					_departure_state,
					_departure_terminal_id 
					FROM public."getDeparturePunchRecord"(tmp_row_record.emp_code::text,daySelect::date) gdpr(id Integer, departure_time time,departure_state text, departure_terminal_id Integer);

					-- Diffrence de temps : On ne peut calculer le temps que si departure_time et arrival_time sont non nul
					if _departure_time::time IS NOT NULL AND _arrival_time::time IS NOT NULL then
						_presence_periode = _departure_time::time - _arrival_time::time;
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
	
select * from public."log_transaction"(); --as (id int, emp_codes char varying,punch_times timestamp) ;

--================ doArrival_refresh =======================

DROP FUNCTION IF EXISTS public."doarrivalrefresh"() CASCADE;

CREATE OR REPLACE FUNCTION public."doarrivalrefresh"() 
returns SETOF text
	language 'plpgsql'
AS $$ 
DECLARE
    _result RECORD;
    _rec RECORD;
	 _row_record RECORD;
	 
	_daySelect date;
	_status text;
	
	_arrival_state text;
    _arrival_time time;
	_arrival_id Integer;
	_arrival_terminal_id Integer;

	dateSelected timestamp;
	
BEGIN

	dateSelected := CURRENT_TIMESTAMP::date;

    if dateSelected IS NOT NULL THEN

        DROP TABLE IF EXISTS h_arrival_punch CASCADE;

        CREATE TABLE h_arrival_punch as 
        SELECT pe.id,
            pe.emp_code,
            pe.first_name,
            pe.last_name,
            ephoto,
            pe.edepartment,
            pe.eposition,
            pe.area,
            '00:00:00'::time arrival_time,
            '0' arrival_state,
            1 arrival_id,
            1 arrival_terminal_id

        FROM employee_view pe WHERE 0=1;

        for _row_record in SELECT * FROM employee_view

        loop
			_daySelect := dateSelected::date;
			
            SELECT gapr.id, gapr.arrival_time::time, gapr.arrival_state, gapr.arrival_terminal_id into 
            _arrival_id, _arrival_time, _arrival_state, _arrival_terminal_id 

            FROM public."getArrivalPunchRecord"(_row_record.emp_code::text,_daySelect::date) gapr(
				id Integer,
				arrival_time time,
				arrival_state text,
				arrival_terminal_id Integer
			);

            INSERT INTO h_arrival_punch(
                id ,
                emp_code ,
                first_name ,
                last_name,
                ephoto,
                edepartment,
                eposition,
                area,
                arrival_time,
                arrival_state,
                arrival_id,
                arrival_terminal_id
            ) VALUES(
                _row_record.id,
                _row_record.emp_code,
                _row_record.first_name,
                _row_record.last_name,
                _row_record.ephoto,
                _row_record.edepartment,
                _row_record.eposition,
                _row_record.area,
                _arrival_time,
                _arrival_state,
                _arrival_id,
                _arrival_terminal_id
            );

        end loop;
        
        _status = CURRENT_TIMESTAMP;

    else
        _status = 'DATE_EMPTY';
    END IF;

    EXCEPTION WHEN unique_violation THEN
        		GET STACKED DIAGNOSTICS _status = PG_EXCEPTION_DETAIL;
				
	return next _status;
	
END
$$;


--- TEST DES FUNCTION =====================
select * from public."doarrivalrefresh"() ;

select * from public."getDeparturePunchRecord"('753','2021-02-18'::date)
as (id Integer, departure_time time,departure_state text, departure_terminal_id Integer);

select * from public."getArrivalPunchRecord"('792','2021-02-18'::date) 
as (
	id Integer,
	arrival_time time,
	arrival_state text,
	arrival_terminal_id Integer
);