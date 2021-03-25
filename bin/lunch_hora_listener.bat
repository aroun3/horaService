set PGPASSWORD=admin123
cls
@echo off
psql -h localhost -p 5432 -U postgres -d biotime -f Z:\data\DIGIN\PROJECTS\gestion-visiteur\req_functions.sql 1>Z:\data\DIGIN\PROJECTS\gestion-visiteur\microservice\bin\log\rqte_psql_1.txt 2>Z:\data\DIGIN\PROJECTS\gestion-visiteur\rqte_psql_2.txt