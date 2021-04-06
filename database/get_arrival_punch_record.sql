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
    END IF;
	
	return NEXT _result;
END
$$;

select * from public."getArrivalPunchRecord"('792','2021-02-18'::date) 
as (
	id Integer,
	arrival_time time,
	arrival_state text,
	arrival_terminal_id Integer
);