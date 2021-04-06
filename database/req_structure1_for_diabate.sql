--- table 1 : historique journee valorisee des pointages --------------- 
punch_day, -- journee de pointage
...
it.emp_code, -- matricule employé 
pe.first_name as first_name, -- nom et prenoms
pp.position_name AS eposition, -- fonction
pd.dept_name AS department, -- departement
area
...
------- champs calcules ----------------
arrival, -- timestamp arrivee
arrival_id, -- id pointage arrivee
departure, -- timestamp depart
departure_id, -- id pointage depart
presence, -- temps de presence (en heure), depart - arrivee
arrival_state, -- tot? a lheure? tard? absent?
departure_state, -- absent? tard? a lheure? tot
presence_state, -- Moins normal plus 
is_absent, -- yes? no?


-- Vue : historique periode valorisee ------------------------------
start_day, -- date debut
end_day, -- date fin
emp_code, -- matricule employe
emp_name, -- nom et prenoms employe
emp_position, -- fonction employe
emp_dept, -- departement employe
emp_area, -- site rattachement employe
------- champs calcules ---------------
arrival_min, -- heure min arrivee sur periode
arrival_max, -- heure max arrivee sur periode
arrival_mean, -- heure moyenne arrivee sur periode
departure_min, -- heure min de depart sur la periode
departure_max, -- heure max de depart sur la periode
departure_mean, -- heure moyenne de depart sur la periode
presence_min, -- temps min presence
presence_max, -- temps max presence
presence_mean, -- temps moyen presence
absence, -- nombre absences sur periode
arrival_state_early, -- nombre tot
arrival_state_late, -- nombre tard
arrival_state_ontime, -- nombre a lheure
arrival_state_absent, -- nombre absence
departure_state_early, -- nombre tot
departure_state_late, -- nombre tard
departure_state_ontime, -- nombre a lheure
departure_state_absent, -- nombre absence








min(it.punch_time::time) as first_punch, 
max(it.punch_time::time) as last_punch,

pe.last_name as last_name,
pe.photo AS ephoto,

