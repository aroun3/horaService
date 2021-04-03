 -- Preparation de la requete : recuperation des parametres (parametres des heures)
 WITH realpunch AS (
         SELECT it.emp_code,
            it.punch_time::date AS punch_date,
            pe.first_name,
            pe.last_name,
            pe.photo AS ephoto,
            ( SELECT string_agg(pa.area_name::text, ','::text) AS string_agg
                   FROM personnel_employee_area pea
                     LEFT JOIN personnel_area pa ON pa.id = pea.area_id
                     LEFT JOIN personnel_employee _pe ON _pe.id = pea.employee_id
                  WHERE pea.employee_id = (( SELECT _pe_1.id
                           FROM personnel_employee _pe_1
                          WHERE _pe_1.emp_code::text = it.emp_code::text))) AS area,
            min(it.punch_time::time without time zone) AS arrival_time,

            -- Permettra de de recuperer les parametres des heures par sites ( agences)
            ( SELECT h_params.h_checkin
                   FROM h_params) AS h_checkin,
            ( SELECT h_params.min_checkin
                   FROM h_params) AS min_checkin,
            ( SELECT h_params.late_checkin
                   FROM h_params) AS late_checkin,
            ( SELECT h_params.max_checkin
                   FROM h_params) AS max_checkin
           FROM personnel_employee pe
             LEFT JOIN iclock_transaction it ON it.emp_id = pe.id
          WHERE it.punch_time::date = CURRENT_TIMESTAMP::date
          GROUP BY it.emp_code, (it.punch_time::date), pe.first_name, pe.last_name, pe.photo
        )

        -- recupere les données en appliquant les regles de status (early(1)-ontime(2_-late(3)-absence(4))
 SELECT realpunch.emp_code,
    realpunch.punch_date,
    realpunch.first_name,
    realpunch.last_name,
    realpunch.ephoto,
    realpunch.area,
    realpunch.arrival_time,
        CASE
            WHEN realpunch.arrival_time >= realpunch.min_checkin AND realpunch.arrival_time <= realpunch.h_checkin THEN 'EARLY'::text
            WHEN realpunch.arrival_time >= realpunch.h_checkin AND realpunch.arrival_time <= realpunch.late_checkin THEN 'ONTIME'::text
            WHEN realpunch.arrival_time >= realpunch.late_checkin AND realpunch.arrival_time <= realpunch.max_checkin THEN 'LATE'::text
            ELSE 'ABSENT'::text
        END AS punch_state
   FROM realpunch;