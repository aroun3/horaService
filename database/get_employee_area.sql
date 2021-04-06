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