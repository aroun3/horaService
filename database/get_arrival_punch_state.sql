DROP FUNCTION IF EXISTS public."getArrivalPunchState"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getArrivalPunchState"(empCode text, dateSelected date) returns setof varchar
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
            WHEN min(it.punch_time::time without time zone) >= rec.min_checkin::time AND min(it.punch_time::time without time zone) < rec.h_checkin::time THEN '1'::text
            WHEN min(it.punch_time::time without time zone) >= rec.h_checkin::time AND min(it.punch_time::time without time zone) < rec.late_checkin::time THEN '2'::text
            WHEN min(it.punch_time::time without time zone) >= rec.late_checkin::time AND min(it.punch_time::time without time zone) < rec.max_checkin::time  THEN '3'::text
            ELSE '0'::text
        END as punch_status

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        GROUP BY it.punch_time;
    END IF;
		
	IF _result IS NULL THEN
		_result = 'ABSENT';
	END IF;
	
    RETURN NEXT _result;
END
$$;

select * from public."getArrivalPunchState"('753','2021-02-18'::date);