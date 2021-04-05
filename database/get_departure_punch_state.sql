DROP FUNCTION IF EXISTS public."getDeparturePunchState"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getDeparturePunchState"(empCode text, dateSelected date) returns setof varchar
	language 'plpgsql'
AS $$ 
DECLARE
    _result varchar;
    rec RECORD;
BEGIN
    IF empCode <> '' AND empCode IS NOT NULL THEN

        SELECT * INTO rec FROM h_params LIMIT 1;

        SELECT INTO _result
        CASE
            WHEN max(it.punch_time::time without time zone) >= rec.min_checkout::time AND max(it.punch_time::time without time zone) < rec.h_checkout::time THEN '1'::text
            WHEN max(it.punch_time::time without time zone) >= rec.h_checkout::time AND max(it.punch_time::time without time zone) < rec.late_checkout::time THEN '2'::text
            WHEN max(it.punch_time::time without time zone) >= rec.late_checkout::time AND max(it.punch_time::time without time zone) < rec.max_checkout::time  THEN '3'::text
            ELSE '0'::text
        END as punch_status

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkout::time AND 
		it.punch_time::time <= rec.max_checkout::time 
        GROUP BY it.punch_time;
    END IF;
		
	IF _result IS NULL THEN
		_result = '0 ';
	END IF;
	
    RETURN NEXT _result;
END
$$;

--select * from public."getDeparturePunchState"('753',CURRENT_TIMESTAMP::date);