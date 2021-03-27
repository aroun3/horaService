PGPASSWORD=admin123
psql -h localhost -p 5432 -U diginuser -d horadb -f /bin/req_transaction.sql 1>/bin/log/log_succes.txt 2>/bin/log/log_error.txt
