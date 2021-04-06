
DROP FUNCTION if exists public."updateEmployeeStatus"();

create or replace function public."updateEmployeeStatus"() returns setof varchar
	language 'plpgsql'
as $$ 
declare
	rec RECORD;
	tmp_row RECORD;
	
	first_punch timestamp;
	last_punch timestamp;
	trans_id int;
	emp_status varchar;
	callbackDate timestamp;
	daySelect timestamp;
	res varchar;
	
begin

	select hp.callback_date into callbackDate from h_params as hp WHERE hp.h_group = 'GENERAL' LIMIT 1;
	
	if callbackDate::date is NULL then
		res := 'La date callback est vide';
		
	else
		daySelect := callbackDate::date;

		-- Creer une table temporaire
		--CREATE TEMPORARY TABLE tmp_table (id int,emp_code char varying,punch_times timestamp) ON COMMIT DROP;
		--CREATE TEMPORARY TABLE tmp_short (emp_code char varying) ON COMMIT DROP;
		
		INSERT INTO h_transaction (select * from public."phantom"() where punch_time::timestamp::date = daySelect -- current day date
			as (
				emp_code varchar(20),
				punch_date date,
				first_punch time,
				last_punch time,
				first_name varchar,
				last_name varchar,
				ephoto varchar,
				department varchar,
				eposition varchar,
				area text,
				incomplet boolean,
				early_checkin boolean,
				ontime_checkin boolean,
				late_checkin boolean,
				early_checkout boolean,
				ontime_checkout boolean,
				late_checkout boolean,
				checkin_status boolean,
				checkout_status boolean
			))

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