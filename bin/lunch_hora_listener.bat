set PGPASSWORD=admin123
cls
@echo off
psql -h localhost -p 5432 -U postgres -d biotime -f Z:\data\DIGIN\PROJECTS\gestion-visiteur\bin\req_transaction.sql 1>Z:\data\DIGIN\PROJECTS\gestion-visiteur\microservice\bin\log\log_succes.txt 2>Z:\data\DIGIN\PROJECTS\gestion-visiteur\bin\log\log_error.txt