CREATE OR REPLACE VIEW departurePunch AS

 SELECT
 	pe.*,
	"getDeparturePunchTime"(pe.emp_code::text,'2021-02-18'::date) as departure_time, --CURRENT_TIMESTAMP
	"getDeparturePunchState"(pe.emp_code::text,'2021-02-18'::date) as punch_status --CURRENT_TIMESTAMP
 FROM employee_view pe;
 
 select * FROM departurePunch;