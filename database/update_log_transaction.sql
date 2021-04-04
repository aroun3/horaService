
DROP FUNCTION if exists public."updateEmployeeStatus"();

create or replace function public."updateEmployeeStatus"() returns setof varchar
	language 'plpgsql'
as $$ 
declare
	rec RECORD;
	tmp_row RECORD;
    logDateParam RECORD;
	callbackDate timestamp;
	daySelect timestamp;
    arrival_time timestamp;
	res varchar;
	
begin

	select hld.curr_date as currentDate, hld.nextday as nextDate into logDateParam from h_log_dateparam as hld  LIMIT 1;
	
	if nextDate::date is NULL then
		res := 'La date callback est vide';
		
	else
		arrival_time := select arrival_time from "getArrivalPunchTime"(it.emp_code::text,nextDate::date);

		INSERT INTO h_log_transaction (
            SELECT 
                it.emp_code,
                ,
                arrival_id

            From iclock_transaction it

            WHERE it.punch_time::date = nextDate::date
        )
			)

		-- Recupere le premier pointage et le dernier pointage
		
		res := 'Status OK';
	end if;				   
	
	/*EXCEPTION WHEN unique_violation THEN
        GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;*/
        
	return next res;
	
end
$$;
	
select * from public."updateEmployeeStatus"() as varchar; --as (id int, emp_codes char varying,punch_times timestamp) ;






/*select emp_code,punch_time,
  CASE
    WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') > 0 THEN 'Present'
	WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') < 0 THEN 'Present'
    ELSE ''
  END  
  as status
from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '07:00:00' AND '07:30:00'*/;