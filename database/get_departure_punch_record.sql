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
            WHEN it.punch_time::time without time zone >= rec.min_checkout::time AND it.punch_time::time without time zone < rec.h_checkout::time THEN '1'::text
            WHEN it.punch_time::time without time zone >= rec.h_checkout::time AND it.punch_time::time without time zone < rec.late_checkout::time THEN '2'::text
            WHEN it.punch_time::time without time zone >= rec.late_checkout::time AND it.punch_time::time without time zone < rec.max_checkout::time  THEN '3'::text
            ELSE '0'::text
        END as departure_state,
		it.terminal_id as departure_terminal_id
		
        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkout::time AND 
		it.punch_time::time <= rec.max_checkout::time 
        ORDER BY it.punch_time ASC LIMIT 1;
    END IF;
	
    RETURN NEXT _result;
END
$$;

select * from public."getDeparturePunchRecord"('753','2021-02-18'::date)
as (id Integer, departure_time time,departure_state text, departure_terminal_id Integer);