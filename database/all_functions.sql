-- ================ Fonction pour obtenir le status arrival ==============
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
        min(it.punch_time::time without time zone) as punch_time,
        CASE
            WHEN min(it.punch_time::time without time zone) BETWEEN rec.min_checkin::time AND rec.h_checkin::time THEN 'EARLY'::text
            WHEN min(it.punch_time::time without time zone) BETWEEN rec.h_checkin::time AND rec.late_checkin::time THEN 'ONTIME'::text
            WHEN min(it.punch_time::time without time zone) BETWEEN rec.late_checkin::time AND rec.max_checkin::time  THEN 'LATE'::text
            ELSE 'ABSENT'::text
        END as punch_status

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        GROUP BY it.emp_code, it.punch_time;
    END IF;
		
	IF _result IS NULL THEN
		_result = 'ABSENT';
	END IF;
	
    RETURN NEXT _result;
END
$$;

--select * from public."getArrivalPunchState"('753',CURRENT_TIMESTAMP::date);

--===============Fonction pour obtenir l'heure min du pointage ===========
DROP FUNCTION IF EXISTS public."getArrivalPunchTime"(empCode text, dateSelected date ) CASCADE;

CREATE OR REPLACE FUNCTION public."getArrivalPunchTime"(empCode text, dateSelected date) returns setof varchar
	language 'plpgsql'
AS $$ 
DECLARE
    _result varchar;
    rec RECORD;
BEGIN
    IF empCode <> '' AND empCode IS NOT NULL THEN

        SELECT * INTO rec FROM h_params LIMIT 1;

        SELECT INTO _result
        min(it.punch_time::time without time zone) as punch_time

        FROM iclock_transaction it
        WHERE it.emp_code = empCode AND 
		it.punch_time::date = dateSelected::date AND 
		it.punch_time::time >= rec.min_checkin::time AND 
		it.punch_time::time <= rec.max_checkin::time 
        GROUP BY it.emp_code, it.punch_time;
    END IF;
		
	IF _result IS NULL THEN
		_result = '';
	END IF;
	
    RETURN NEXT _result;
END
$$;

--select * from public."getArrivalPunchTime"('753',CURRENT_TIMESTAMP::date);

--=============== Fonction renvoyant area d'un employé=========
DROP FUNCTION IF EXISTS public."getEmployeeArea"(empId Integer);

CREATE OR replace FUNCTION public."getEmployeeArea"(empId Integer) returns setof varchar
	language 'plpgsql'
AS $$ 
DECLARE
    res varchar;
BEGIN
    IF empId > 0 AND empId IS NOT NULL THEN
        SELECT string_agg(pa.area_name::text, ','::text) AS string_agg INTO res
        FROM personnel_area pa 
        WHERE pa.id IN (SELECT pea.area_id FROM personnel_employee_area as pea WHERE pea.employee_id = empId);
    END IF;

    RETURN NEXT res;
END
$$;

--select * from public."getEmployeeArea"(1) as varchar;