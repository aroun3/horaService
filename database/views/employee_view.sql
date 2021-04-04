DROP VIEW IF EXISTS employee_view CASCADE;
CREATE OR REPLACE VIEW employee_view AS 
SELECT 
    pe.id,
    pe.emp_code,
    pe.first_name,
    pe.last_name,
    pe.photo AS ephoto,
	(SELECT pd.dept_name FROM personnel_department pd WHERE pd.id = pe.department_id LIMIT 1) as edepartment,
	(SELECT pp.position_name FROM personnel_position pp WHERE pp.id = pe.position_id LIMIT 1) as eposition,
    public."getEmployeeArea"(pe.id) as area

FROM personnel_employee pe

WHERE pe.emp_code <> '';

select * From employee_view;