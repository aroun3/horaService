SELECT pe.emp_code, 
pe.first_name, 
pe.last_name, 
hlt.arrival_time,
CASE
	WHEN hlt.arrival_state = '0' THEN 'NON DISPONIBLE'
	WHEN hlt.arrival_state = '1' THEN 'ARRIVEE TOT'
	WHEN hlt.arrival_state = '2' THEN 'ARRIVEE A L HEURE'
	WHEN hlt.arrival_state = '3' THEN 'ARRIVEE TARD'
END as statut_arrivee,
hlt.departure_time,
CASE
WHEN hlt.departure_state = '0' THEN 'NON DISPONIBLE'
WHEN hlt.departure_state = '1' THEN 'DEPART TOT'
WHEN hlt.departure_state = '2' THEN 'DEPART A L HEURE'
WHEN hlt.departure_state = '3' THEN 'DEPART RETARDE'
END as statut_depart,

hlt.presence_periode,
hlt.presence_state as status_pointage,
hlt.log_date as date_pointage


FROM personnel_employee pe, h_log_transaction hlt 
where hlt.emp_code = pe.emp_code;