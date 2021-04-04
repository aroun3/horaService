
DROP FUNCTION if exists public."updateEmployeeStatus"();

create or replace function public."updateEmployeeStatus"() returns setof varchar
	language 'plpgsql'
as $$ 
declare
	history_record RECORD;
	tmp_row_record RECORD;
    logDateParam RECORD;
	callbackDate timestamp;
	daySelect timestamp;

	arrival_time time;
	arrival_id Integer;
	
	departure_time time;
	departure_id Integer;
	
	presence_periode time;
	
	arrival_state text;
	departure_state text;
	presence_state text;
	is_absent boolean;
	
	res varchar;
	
begin

	select hld.curr_date as currentDate, hld.nextdate as nextDate into logDateParam from h_log_dateparam hld  LIMIT 1;
	
	for tmp_row_record in SELECT * FROM iclock_transaction it WHERE it.punch_time::date = '2021-02-18'::date
		
		loop
			
			
				--raise notice 'emp_code: % ',tmp_row_record.emp_code;
				SELECT gapr.id, gapr.arrival_time::time into arrival_id,arrival_time FROM public."getArrivalPunchRecord"(tmp_row_record.emp_code::text,'2021-02-18'::date) gapr(id Integer,arrival_time time);
				SELECT gdpr.id, gdpr.departure_time::time into departure_id,departure_time FROM public."getDeparturePunchRecord"(tmp_row_record.emp_code::text,'2021-02-18'::date) gdpr(id Integer, departure_time time);
				
				-- Diffrence de temps
				if departure_time <> NULL AND arrival_time <> NULL then
					presence_periode = arrival_time - departure_time;
				else
					presence_periode = '00:00:00';
				end if;
				
				arrival_state = public."getArrivalPunchState"(tmp_row_record.emp_code::text,'2021-02-18'::date);
				departure_state = public."getDeparturePunchState"(tmp_row_record.emp_code::text,'2021-02-18'::date);
				SELECT INTO presence_state 
				CASE 
					WHEN presence_periode < '08:00:00' THEN 'MOINS'
					WHEN presence_periode > '08:00:00' THEN 'PLUS'
					ELSE 'NORMAL'
				END;
				
				SELECT INTO is_absent
				CASE 
					WHEN arrival_state <> '0' AND departure_state <> '0' THEN false
					ELSE true
				END;
				
				raise notice 'emp_code: % - arrival_time : % - arrival_id : % - departure_time : % - departure_id : % - presence_periode : % - arrival_state : % - departure_state : % -presence_periode: % - is_absent : %',
				tmp_row_record.emp_code,arrival_time,arrival_id,departure_time,departure_id,presence_periode,arrival_state,departure_state, presence_periode,is_absent;
	
		
			/*INSERT INTO tmp_log_transaction VALUE(
				tmp_row.emp_code::text,
				arrival_time,
				arrival_id,
				departure_time,
				departure_id,
				presence_periode,
				
				
			)*/
			
		end loop;
	/*if nextDate::date is NULL then
		res := 'La date callback est vide';
		
	else
		CREATE TEMPORARY TABLE tmp_log_transaction (
			id integer NOT NULL,
			emp_code character varying(20)  NOT NULL,
			arrival_time timestamp with time zone,
			arrival_id smallint,
			departure_time timestamp with time zone,
			departure_id smallint,
			presence_periode timestamp,
			arrival_state smallint,
			departure_state smallint,
			presence_state character varying(10),
			is_absent boolean,
			CONSTRAINT tmp_log_transaction_pkey PRIMARY KEY (id)
		) ON COMMIT DROP;
		
		for tmp_row_record in SELECT * FROM iclock_transaction it WHERE it.punch_time::date = '2021-02-18'::date
		loop
			if tmp_row_record IS NOT NULL then
				SELECT gapr.id, gapr.arrival_time into arrival_id,arrival_time FROM "getArrivalPunchRecord"(tmp_row.emp_code::text,'2021-02-18'::date) gapr;
				SELECT gdpr.id, gdpr.departure_time into departure_id,departure_time FROM "getDeparturePunchRecord"(tmp_row.emp_code::text,'2021-02-18'::date) gdpr;
				presence_periode = gapr.arrival_time::time - gdpr.departure_time::time;
				arrival_state = "getArrivalPunchState"(tmp_row.emp_code::text,'2021-02-18'::date);
				departure_state = "getDeparturePunchState"(tmp_row.emp_code::text,'2021-02-18'::date);
				SELECT INTO presence_state 
				CASE 
					WHEN presence_periode < 8 THEN 'MOINS'
					WHEN presence_periode > 8 THEN 'PLUS'
					ELSE 'NORMAL'
				END;
				
				raise notice 'emp_code: % - arrival_time : % - arrival_id : % - departure_time : % - departure_id : % - presence_periode : % - arrival_state : %',
				tmp_row.emp_code,arrival_time,arrival_id,departure_time,departure_id,presence_periode,arrival_state;
			end if;
		
			/*INSERT INTO tmp_log_transaction VALUE(
				tmp_row.emp_code::text,
				arrival_time,
				arrival_id,
				departure_time,
				departure_id,
				presence_periode,
				
				
			)*/
			
		end loop;
		
		res := 'Status OK';
	end if;	*/			   
	
	/*EXCEPTION WHEN unique_violation THEN
        GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;*/
        
	return next res;
	
end
$$;
	
select * from public."updateEmployeeStatus"(); --as (id int, emp_codes char varying,punch_times timestamp) ;






/*select emp_code,punch_time,
  CASE
    WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') > 0 THEN 'Present'
	WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') < 0 THEN 'Present'
    ELSE ''
  END  
  as status
from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '07:00:00' AND '07:30:00'*/;