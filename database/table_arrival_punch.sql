
CREATE TABLE public.h_arrival_punch
(
    id integer,
    emp_code character varying(20),
    first_name character varying(25),
    last_name character varying(25),
    ephoto character varying(200),
    edepartment character varying(100),
    eposition character varying(100),
    area character varying COLLATE pg_catalog."default",
    arrival_time time without time zone,
    arrival_state text COLLATE pg_catalog."default",
    arrival_id integer,
    arrival_terminal_id integer
)
