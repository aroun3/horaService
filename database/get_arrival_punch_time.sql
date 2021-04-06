DROP FUNCTION IF EXISTS public."getArrivalPunchTime"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getArrivalPunchTime"(empCode text, dateSelected date) returns setof varchar
	language 'plpgsql'
AS $$ 
DECLARE
    _result TIMESTAMP;
    rec RECORD;
BEGIN
    IF empCode <> '' AND empCode IS NOT NULL THEN

        SELECT * INTO rec FROM h_params LIMIT 1;

        SELECT INTO _result
        	min(it.punch_time::time without time zone)
        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        GROUP BY it.punch_time;
    END IF;
		
	IF _result IS NULL THEN
		_result = NULL;
	END IF;
	
    RETURN NEXT _result;
END
$$;

--select * from public."getArrivalPunchTime"('753',CURRENT_TIMESTAMP::date);