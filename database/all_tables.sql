CREATE TABLE IF NOT EXISTS public.h_arrival_punch
(
    id integer,
    emp_code character varying(20),
    first_name character varying(25),
    last_name character varying(25),
    ephoto character varying(200),
    edepartment character varying(100),
    eposition character varying(100),
    area character varying,
    arrival_time time without time zone,
    arrival_state text,
    arrival_id integer,
    arrival_terminal_id integer
)

CREATE TABLE IF NOT EXISTS public.h_log_transaction
(
    id serial NOT NULL,
    emp_code character varying(20)  NOT NULL,
    arrival_time time with time zone,
    arrival_id smallint,
    arrival_terminal_id integer,
    departure_time time with time zone,
    departure_id smallint,
    departure_terminal_id integer,
    presence_periode time,
    arrival_state text,
    departure_state text,
    presence_state text,
    is_absent boolean,
    log_date TIMESTAMP,
    CONSTRAINT h_log_transaction_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.h_log_dateparam
(
    id integer NOT NULL,
    curr_date timestamp with time zone,
    nextdate timestamp with time zone,
    CONSTRAINT h_log_dateparam_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.h_gps_area
(
    id serial NOT NULL,
    longitude double precision,
    latitude double precision,
    area_id integer,
    area_name character ,
    CONSTRAINT h_area_gps PRIMARY KEY (id)
)
