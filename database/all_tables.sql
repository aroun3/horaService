CREATE TABLE public.h_params
(
    id serial NOT NULL ,
    min_checkin time without time zone,
    h_checkin time without time zone,
    late_checkin time without time zone,
    max_checkin time without time zone,

    min_checkout time without time zone,
    early_checkout time without time zone,
    h_checkout time without time zone,
    max_checkout time without time zone,

    callback_date date,
    area_id integer,
    h_group character varying(255),
    CONSTRAINT h_params_pkey PRIMARY KEY (id)
)

INSERT INTO public.h_params(
    min_checkin,
    h_checkin,
    late_checkin,
    max_checkin,

    min_checkout,
    early_checkout,
    h_checkout,
    max_checkout,

    h_group
)
VALUES(
    '02:00:00',
    '07:30:00',
    '07:45:00',
    '12:00:00',

    '14:00:00',
    '15:45:00',
    '16:00:00',
    '22:00:00',

    'GENERAL'
)


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
    id serial NOT NULL,
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
    area_name character varying(100),
    CONSTRAINT h_area_gps PRIMARY KEY (id)
)

insert into h_gps_area (id,area_id,area_name,latitude,longitude) VALUES 
(1,1,'Pas autorisé',null,null),	
(2,2,'TOUS LES SITES',null,null),	
(3,3,'SIEGE',5.29010,-3.99042),
(4,4,'AGENCE YOPOUGON I',5.332060,-4.05237),
(5,5,'SALLE DEDIEE YOPOUGON I',5.332140,-4.0688),
(6,6,'PLR YOPOUGON I',5.31990,-4.04413),
(7,7,'AGENCE YOPOUGON II',null,null),
(8,8,'AGENCE BIETRY',5.27267,-3.97470),
(9,9,'AGENCE DU VALLON',5.356827,-3.9893),
(10,10,'SALLE DEDIEE SPORTCASH',null,null),
(11,11,'BUREAU ANNEXE RUE MERCEDES',null,null),
(12,12,'AGENCE PLATEAU',5.32358,-4.0220),
(13,13,'PLR TREICHVILLE',null,null),
(14,14,'PLR VALLON',null,null),
(15,15,'AGENCE ABOBO',null,null),
(16,16,'PLR ABOBO 1 (Gendarmerie)',5.424323,-4.01953),
(17,17,'PLR ABOBO 2 (Mairie)',5.41480,-4.0065),
(18,18,'AGENCE DALOA',null,null),
(19,19,'AGENCE SAN PEDRO',null,null),
(20,20,'SALLE DEDIEE SAN PEDRO',null,null),
(21,21,'AGENCE DE BOUAKE',null,null),
(22,22,'PLR BOUAKE',null,null),
(23,23,'AGENCE DE YAMOUSSOUKRO',null,null),
(24,24,'SALLE DEDIEE YAMOUSSOUKRO',null,null),
(25,25,'AGENCE ABENGOUROU',null,null),
(26,26,'AGENCE GAGNOA',null,null),
(27,27,'SALLE DEDIEE GAGNOA',null,null),
(28,28,'AGENCE KORHOGO',null,null),
(29,29,'AGENCE ABOISSO',null,null),
(30,30,'AGENCE MAN',7.410488,-7.54974);
