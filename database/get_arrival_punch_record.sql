DROP FUNCTION IF EXISTS public."getArrivalPunchTimeRecord"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getArrivalPunchTimeRecord"(empCode text, dateSelected date) 
returns TABLE(id Integer, arrival_time time)
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
        	min(it.punch_time::time without time zone) as arrival_time
        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        GROUP BY it.punch_time;
    END IF;
	
    RETURN NEXT _result;
END
$$;

--select * from public."getArrivalPunchTime"('753',CURRENT_TIMESTAMP::date);