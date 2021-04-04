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
        max(it.punch_time::time without time zone) as departure_time

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkout::time AND 
		it.punch_time::time <= rec.max_checkout::time 
        GROUP BY it.id, it.punch_time;
    END IF;
	
    RETURN NEXT _result;
END
$$;

select * from public."getDeparturePunchRecord"('753','2021-02-18'::date) as (id Integer, departure_time time);