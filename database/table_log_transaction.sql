CREATE TABLE public.h_log_transaction
(
    id integer NOT NULL,
    emp_code character varying(20)  NOT NULL,
    arrival_time timestamp with time zone,
    arrival_id smallint,
    departure_time timestamp with time zone,
    departure_id smallint,
    presence_periode timestamp,
    arrival_state smallint,
    departure_state smallint,
    presence_state character varying(10),
    is_absent boolean,
    CONSTRAINT h_log_transaction_pkey PRIMARY KEY (id)
)

CREATE TABLE public.h_log_dateparam
(
    id integer NOT NULL,
    curr_date timestamp with time zone,
    nextdate timestamp with time zone,
    CONSTRAINT h_log_dateparam_pkey PRIMARY KEY (id)
)