/*Nombre de pointage par jour*/
SELECT id, emp_code, punch_time, punch_state, verify_type, work_code, terminal_sn, terminal_alias, area_alias, longitude, latitude, gps_location, mobile, source, purpose, crc, is_attendance, reserved, upload_time, sync_status, sync_time, emp_id, terminal_id
	FROM public.iclock_transaction WHERE punch_time = clock_timestamp();

/*tronque la date*/

SELECT id, emp_code,  punch_time::timestamp::date, punch_state, verify_type, work_code, terminal_sn, terminal_alias, area_alias, longitude, latitude, gps_location, mobile, source, purpose, crc, is_attendance, reserved, upload_time, sync_status, sync_time, emp_id, terminal_id
	FROM public.iclock_transaction ;

/*OK*/
SELECT id, emp_code,  punch_time,terminal_sn,area_alias, longitude, latitude, gps_location, mobile, source, purpose, crc, is_attendance, reserved, upload_time, sync_status, sync_time, emp_id, terminal_id
FROM public.iclock_transaction WHERE punch_time::timestamp::date = current_date::date;

SELECT count(id)
FROM public.iclock_transaction WHERE punch_time::timestamp::date = current_date::date;

/*
SELECT count(id)
	FROM public.iclock_transaction WHERE punch_time::timestamp::date = '2021-03-02'::date;
*/


SELECT id, (SELECT personnel_employee.first_name FROM public.personnel_employee WHERE personnel_employee.emp_code = iclock_transaction.emp_code),  punch_time, punch_state, verify_type, work_code, terminal_sn, terminal_alias, area_alias, longitude, latitude, gps_location, mobile, source, purpose, crc, is_attendance, reserved, upload_time, sync_status, sync_time, emp_id, terminal_id
	FROM public.iclock_transaction WHERE punch_time::timestamp::date = '2021-03-02'::date;

/*Example insert OK*/
INSERT INTO public.h_params(
	h_checkin, h_checkout, h_group, hearly_checkout, late_checkin, max_checkin, max_chectout, min_checkin, min_checkout)
	VALUES ('07:00:00','18:00:00', 'GENERAL', '17:30:00', '07:15:00', '12:00:00', '21:00:00', '17:00:00', '02:00:00');

select emp_code,punch_time from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '08:00:00' AND '10:00:00';

/* Function */
DROP FUNCTION if exists public."getDateListy"(daySelect timestamp);

CREATE OR REPLACE FUNCTION public."getDateListy"(daySelect timestamp) RETURNS SETOF RECORD
	LANGUAGE 'plpgsql'
AS $$ 
DECLARE
	rec RECORD;
	temp_row RECORD;
begin
	-- Creer une table temporaire
	CREATE TEMPORARY TABLE temp_table (emp_codes char varying,punch_times timestamp) ON COMMIT DROP;
	--return query;
	
	-- Faire ressortir la liste des pointage du jour
	FOR temp_row IN select emp_code,punch_time FROM public.iclock_transaction WHERE punch_time::timestamp::date = daySelect
	LOOP
		
		IF temp_row.emp_code <> '' THEN
			--RAISE NOTICE 'sql: %', temp_row.sql;
			EXECUTE format('INSERT INTO temp_table VALUES(''%s'',''%s'')', temp_row.emp_code,temp_row.punch_time);

			SELECT temp_table.emp_codes, temp_table.punch_times
			INTO rec
			FROM temp_table;
			
			return next rec;
		END IF;
		
		RAISE NOTICE 'emp_code: %',temp_row.emp_code;
	END LOOP;
	
end
$$;
	
select * from public."getDateListy"('2021-02-18'::date) as (emp_codes char varying,punch_times timestamp) ;

/*select emp_code,punch_time,
  CASE
    WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') > 0 THEN 'Present'
	WHEN (select count(*) from iclock_transaction WHERE emp_code = emp_code AND  punch_time::timestamp::time BETWEEN '08:00:00' AND '10:30:00') < 0 THEN 'Present'
    ELSE ''
  END  
  as status
from iclock_transaction WHERE punch_time::timestamp::time BETWEEN '07:00:00' AND '07:30:00'*/;