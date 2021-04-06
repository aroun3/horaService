
CREATE TABLE public.h_arrival_punch
(
    id integer,
    emp_code character varying(20) COLLATE pg_catalog."default",
    first_name character varying(25) COLLATE pg_catalog."default",
    last_name character varying(25) COLLATE pg_catalog."default",
    ephoto character varying(200) COLLATE pg_catalog."default",
    edepartment character varying(100) COLLATE pg_catalog."default",
    eposition character varying(100) COLLATE pg_catalog."default",
    area character varying COLLATE pg_catalog."default",
    arrival_time time without time zone,
    arrival_state text COLLATE pg_catalog."default",
    arrival_id integer,
    arrival_terminal_id integer
)
