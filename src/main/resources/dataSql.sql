CREATE TABLE public.h_transactions
(
    id integer NOT NULL DEFAULT nextval('h_transactions_id_seq'::regclass),
    code_emp character varying(255) COLLATE pg_catalog."default",
    date_trans date,
    first_punch time without time zone,
    last_punch time without time zone,
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT h_transactions_pkey PRIMARY KEY (id)
)
