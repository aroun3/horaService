PGPASSWORD=admin123
psql -h localhost -p 5432 -U diginuser -d horadb -f /media/aroun3/DATA/DIGIN/PROJECTS/gestion-visiteur/req_functions.sql 1>/media/aroun3/DATA/DIGIN/PROJECTS/gestion-visiteur/microservice/bin/log/rqte_psql_1.txt 2>/media/aroun3/DATA/DIGIN/PROJECTS/gestion-visiteur/rqte_psql_2.txt
