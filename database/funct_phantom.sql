DROP FUNCTION if exists public."listEmployeeStatus"();

create or replace function public."listEmployeeStatus"() returns setof RECORD
	language 'plpgsql'
as $$ 
declare
	rec RECORD;
	res RECORD;
	daySelect timestamp;
	callbackDate timestamp;
	
begin
	
	-- Date to start with
	--select hp.callback_date into callbackDate from h_params as hp WHERE hp.h_group = 'GENERAL' LIMIT 1;
	
	daySelect := '2021-02-18'::date; --callbackDate::date;

	select h_params.* into rec from h_params; -- getparams (must focus on emplyee specifique params)
	raise notice 'RISE %',rec.h_checkin;
	
	for res in select 
		
		it.emp_code,
		it.punch_time::date as punch_date,
		min(it.punch_time::time) as first_punch, 
		max(it.punch_time::time) as last_punch,
		pe.first_name as first_name,
		pe.last_name as last_name,
		pe.photo AS ephoto,
		pd.dept_name AS department,
		pp.position_name AS eposition,
		(select string_agg(pa.area_name, ',') 
		from personnel_employee_area pea
		left join personnel_area pa on pa.id = pea.area_id
		 left join personnel_employee _pe on _pe.id = pea.employee_id
		 WHERE pea.employee_id = (select _pe.id from personnel_employee _pe where _pe.emp_code = it.emp_code)
		 
		) as area,
		
		-- Incomplet
		case
			When min(it.punch_time::time) = max(it.punch_time::time) then true
			else false
		end  as incomplet,
		
		-- avnt l'heure
		case
			when min(it.punch_time::time) BETWEEN rec.min_checkin::time AND rec.h_checkin::time then true
			else false
		end as early_checkin,
		
		-- A l'heure
		case 
			when min(it.punch_time::time) BETWEEN rec.h_checkin::time AND rec.late_checkin::time then true
			else false
		end as ontime_checkin,
		
		-- Retard
		case 
			when min(it.punch_time::time) BETWEEN rec.late_checkin::time AND rec.max_checkin::time then true
			else false
		end as late_checkin,
		
		-- Depart anticipé
		case
			when max(it.punch_time::time) BETWEEN rec.min_checkout::time AND rec.h_checkout::time then true
			else false
		end as early_checkout,
	
		-- depart dans le temps
		case 
			when max(it.punch_time::time) BETWEEN rec.h_checkout::time AND rec.early_checkout::time then true
			else false
		end as ontime_checkout,
		
		-- Depart retardé
		case 
			when max(it.punch_time::time) BETWEEN rec.early_checkout::time AND rec.max_checkout::time then true
			else false
		end as late_checkout,
		
		-- entrée
		case
			when (min(it.punch_time::time) BETWEEN rec.min_checkin::time AND rec.h_checkin::time) 
			or (min(it.punch_time::time) BETWEEN rec.h_checkin::time AND rec.late_checkin::time)
			or (min(it.punch_time::time) BETWEEN rec.late_checkin::time AND rec.max_checkin::time) then true
			else false
		end  as checkin_status,

		-- sortie
		case
			when (max(it.punch_time::time) BETWEEN rec.min_checkout::time AND rec.h_checkout::time)
			or (max(it.punch_time::time) BETWEEN rec.h_checkout::time AND rec.early_checkout::time)
			or (max(it.punch_time::time) BETWEEN rec.early_checkout::time AND rec.max_checkout::time) then true
			else false
		end  as checkout_status
	
		from iclock_transaction it 
		LEFT JOIN personnel_employee pe ON it.emp_id = pe.id
		 LEFT JOIN personnel_department pd ON pe.department_id = pd.id
		 LEFT JOIN personnel_position pp ON pe.position_id = pp.id
		--WHERE it.punch_time::date = '2021-02-18'::date 
		GROUP BY it.emp_code,it.punch_time::date,pe.first_name, pe.last_name,pe.photo,pd.dept_name, pp.position_name
		loop
		return next res;
	end loop;
	
	/*EXCEPTION WHEN unique_violation THEN
        GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;*/
end
$$;
	
select * from public."listEmployeeStatus"() 
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
		incomplet bool,
		early_checkin bool,
		ontime_checkin bool,
		late_checkin bool,
		early_checkout bool,
		ontime_checkout bool,
		late_checkout bool,
		checkin_status bool,
		checkout_status bool
	);






/*select emp_code,punch_time,
  CASE
    WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') > 0 THEN 'Present'
	WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') < 0 THEN 'Present'
    ELSE ''
  END  
  as status
from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '07:00:00' AND '07:30:00'*/;