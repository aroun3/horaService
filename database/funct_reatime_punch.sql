-- 1: EARLY
-- 2: ONTIME
-- 3: LATE
-- 4: ABSENT

DROP FUNCTION if exists public."listArrival"();

CREATE OR REPLACE FUNCTION public."listArrival"() returns setof RECORD
	LANGUAGE 'plpgsql'
AS $$ 
DECLARE
	rec RECORD;
	res RECORD;
	callbackDate timestamp;
	
BEGIN
	SELECT h_params.* INTO rec FROM h_params; -- Sur  cette table on regupere le parametrage horaire d'une agence
	raise notice 'RISE %',rec.h_checkin;
	
	for res in select 
		it.emp_code,
		it.punch_time::date as punch_date,
		pe.first_name as first_name,
		pe.last_name as last_name,
		pe.photo AS ephoto,
		(SELECT string_agg(pa.area_name, ',') 
			FROM personnel_employee_area pea
			LEFT JOIN personnel_area pa ON pa.id = pea.area_id
		 	LEFT JOIN personnel_employee _pe ON _pe.id = pea.employee_id
		 	WHERE pea.employee_id = (SELECT _pe.id FROM personnel_employee _pe WHERE _pe.emp_code = it.emp_code)
		 
		) as area,
		min(it.punch_time::time) as arrival_time, 
		case
			when min(it.punch_time::time) BETWEEN rec.min_checkin::time AND rec.h_checkin::time then 'EARLY'
			when min(it.punch_time::time) BETWEEN rec.h_checkin::time AND rec.late_checkin::time then 'ONTIME'
			when min(it.punch_time::time) BETWEEN rec.late_checkin::time AND rec.max_checkin::time then 'LATE'
			else 'ABSENT'
		end  as punch_state
		
		from personnel_employee pe
		LEFT JOIN iclock_transaction it ON   it.emp_id = pe.id
		WHERE it.punch_time::date = current_timestamp::date
		GROUP BY it.emp_code,it.punch_time::date,pe.first_name, pe.last_name,pe.photo
		loop
		
		return next res;
	end loop;
	
	/*EXCEPTION WHEN unique_violation THEN
        GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;*/
end
$$;
	
select * from public."listArrival"() 
	as (
		emp_code varchar(20),
		punch_date date,
		first_name varchar,
		last_name varchar,
		ephoto varchar,
		area text,
		arrival_time time,
		punch_state text
	);






/*select emp_code,punch_time,
  CASE
    WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') > 0 THEN 'Present'
	WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') < 0 THEN 'Present'
    ELSE ''
  END  
  as status
from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '07:00:00' AND '07:30:00'*/;