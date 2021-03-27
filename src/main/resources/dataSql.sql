/* TABLE recevant les données par le script tache programmé : historique des transactions*/

CREATE TABLE IF NOT exists public.h_transactions
(
    id integer NOT NULL,
    emp_code varchar(20) NOT NUL,
    punch_date date NOT NULL,
    first_punch time NOT NULL,
    last_punch time NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    ephoto varchar NULL,
    department varchar NOT NULL,
    eposition varchar NOT NULL,
    area text NOT NULL,
    incomplet boolean NOT NULL,
    early_checkin boolean NOT NULL,
    ontime_checkin boolean NOT NULL,
    late_checkin boolean NOT NULL,
    early_checkout boolean NOT NULL,
    ontime_checkout boolean NOT NULL,
    late_checkout boolean NOT NULL,
    checkin_status boolean NOT NULL,
    checkout_status boolean NOT NULL
)

/* Phantom fonction */

DROP FUNCTION if exists public."phantom"();

create or replace function public."phantom"() returns setof RECORD
	language 'plpgsql'
as $$ 
declare
	rec RECORD;
	res RECORD;
	daySelect timestamp;
	callbackDate timestamp;
	
begin
	
	-- Date to start with
	--select hp.callback_date into callbackDate from h_params as hp WHERE hp.h_group = 'GENERAL' LIMIT 1;
	
    /* =========just to test =============== */
	daySelect := '2021-02-18'::date; --callbackDate::date;
    /* ===================================== */

	select h_params.* into rec from h_params; -- getparams (must focus on emplyee specifique params)
	
    raise notice 'RISE %',rec.h_checkin;
	
	for res in select 
		
		it.emp_code,
		it.punch_time::date as punch_date,
		min(it.punch_time::time) as first_punch, 
		max(it.punch_time::time) as last_punch,
		pe.first_name as first_name,
		pe.last_name as last_name,
		pe.photo AS ephoto,
		pd.dept_name AS department,
		pp.position_name AS eposition,
		(select string_agg(pa.area_name, ',') 
		from personnel_employee_area pea
		left join personnel_area pa on pa.id = pea.area_id
		left join personnel_employee _pe on _pe.id = pea.employee_id
		where pea.employee_id = (select _pe.id from personnel_employee _pe where _pe.emp_code = it.emp_code)
		 
		) as area,
		
		-- Incomplet
		case
			When min(it.punch_time::time) = max(it.punch_time::time) then true
			else false
		end  as incomplet,
		
		-- avnt l'heure
		case
			when min(it.punch_time::time) between rec.min_checkin::time and rec.h_checkin::time then true
			else false
		end as early_checkin,
		
		-- A l'heure
		case 
			when min(it.punch_time::time) between rec.h_checkin::time and rec.late_checkin::time then true
			else false
		end as ontime_checkin,
		
		-- Retard
		case 
			when min(it.punch_time::time) between rec.late_checkin::time and rec.max_checkin::time then true
			else false
		end as late_checkin,
		
		-- Depart anticipé
		case
			when max(it.punch_time::time) between rec.min_checkout::time and rec.h_checkout::time then true
			else false
		end as early_checkout,
	
		-- depart dans le temps
		case 
			when max(it.punch_time::time) between rec.h_checkout::time and rec.early_checkout::time then true
			else false
		end as ontime_checkout,
		
		-- Depart retardé
		case 
			when max(it.punch_time::time) between rec.early_checkout::time and rec.max_checkout::time then true
			else false
		end as late_checkout,
		
		-- entrée
		case
			when (min(it.punch_time::time) between rec.min_checkin::time and rec.h_checkin::time) 
			or (min(it.punch_time::time) between rec.h_checkin::time and rec.late_checkin::time)
			or (min(it.punch_time::time) between rec.late_checkin::time and rec.max_checkin::time) then true
			else false
		end  as checkin_status,

		-- sortie
		case
			when (max(it.punch_time::time) between rec.min_checkout::time and rec.h_checkout::time)
			or (max(it.punch_time::time) between rec.h_checkout::time and rec.early_checkout::time)
			or (max(it.punch_time::time) between rec.early_checkout::time and rec.max_checkout::time) then true
			else false
		end  as checkout_status
	
		from iclock_transaction it 
		left join personnel_employee pe on it.emp_id = pe.id
		 left join personnel_department pd on pe.department_id = pd.id
		 left join personnel_position pp on pe.position_id = pp.id
		--WHERE it.punch_time::date = '2021-02-18'::date 
		group by it.emp_code,it.punch_time::date,pe.first_name, pe.last_name,pe.photo,pd.dept_name, pp.position_name
		loop
		return next res;
	end loop;
	
	/*EXCEPTION WHEN unique_violation THEN
        GET STACKED DIAGNOSTICS res = PG_EXCEPTION_DETAIL;*/
end
$$;
	


/* VUE */
CREATE or REPLACE VIEW h_phantom_view AS

select * from public."phantom"() 
	as (
		emp_code varchar(20),
		punch_date date,
		first_punch time,
		last_punch time,
		first_name varchar,
		last_name varchar,
		ephoto varchar,
		department varchar,
		eposition varchar,
		area text,
		incomplet boolean,
		early_checkin boolean,
		ontime_checkin boolean,
		late_checkin boolean,
		early_checkout boolean,
		ontime_checkout boolean,
		late_checkout boolean,
		checkin_status boolean,
		checkout_status boolean
	);


/* transaction en temps réel */
CREATE or REPLACE VIEW h_realtime_transaction AS
 SELECT it.id,
    it.emp_code,
    it.punch_time::timestamp without time zone::date AS punch_date,
    it.punch_time::timestamp without time zone::time without time zone AS punch_time,
    pe.first_name,
    pe.last_name,
    pe.photo AS ephoto,
    pd.dept_name AS department,
    pp.position_name AS eposition,
    ( SELECT string_agg(pa.area_name::text, ','::text) AS string_agg
           FROM personnel_employee_area pea
             LEFT JOIN personnel_area pa ON pa.id = pea.area_id
             LEFT JOIN personnel_employee _pe ON _pe.id = pea.employee_id
          WHERE pea.employee_id = (( SELECT _pe_1.id
                   FROM personnel_employee _pe_1
                  WHERE _pe_1.emp_code::text = it.emp_code::text))) AS area
   FROM iclock_transaction it
     LEFT JOIN personnel_employee pe ON it.emp_id = pe.id
     LEFT JOIN personnel_department pd ON pe.department_id = pd.id
     LEFT JOIN personnel_position pp ON pe.position_id = pp.id
  ORDER BY it.id DESC;

/*  Initailisation des roles */
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');