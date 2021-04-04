DROP VIEW IF EXISTS realpunch CASCADE;
CREATE OR REPLACE VIEW arrivalPunch AS

 SELECT
 	pe.*,
	"getArrivalPunchTime"(pe.emp_code::text,'2021-02-18'::date) as arrival_time, --CURRENT_TIMESTAMP
	"getArrivalPunchState"(pe.emp_code::text,'2021-02-18'::date) as punch_status --CURRENT_TIMESTAMP
 FROM employee_view pe;
 
 select * FROM realpunch;