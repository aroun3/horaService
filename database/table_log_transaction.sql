CREATE TABLE public.h_log_transaction
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

--ALTER TABLE public.h_log_transaction MODIFY id BIGSERIAL;

CREATE TABLE public.h_log_dateparam
(
    id integer NOT NULL,
    curr_date timestamp with time zone,
    nextdate timestamp with time zone,
    CONSTRAINT h_log_dateparam_pkey PRIMARY KEY (id)
)