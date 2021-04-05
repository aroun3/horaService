DROP VIEW IF EXISTS arrivalPunchView CASCADE;
CREATE OR REPLACE VIEW arrivalPunchView AS

 SELECT
 	pe.*,
	"getArrivalPunchTime"(pe.emp_code::text,'2021-02-18'::date) as arrival_time, --CURRENT_TIMESTAMP
	"getArrivalPunchState"(pe.emp_code::text,'2021-02-18'::date) as punch_status --CURRENT_TIMESTAMP
	-- id du terminal 
	-- id du area du pointage
 FROM employee_view pe;
 
 select * FROM arrivalPunchView;