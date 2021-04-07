DROP FUNCTION IF EXISTS public."doArrivalRefresh"() CASCADE;--dateSelected TIMESTAMP

CREATE OR REPLACE FUNCTION public."doArrivalRefresh"() 
returns SETOF text
	language 'plpgsql'
AS $$ 
DECLARE
    _result RECORD;
    _rec RECORD;
	 _row_record RECORD;
	 
	_daySelect date;
	_status text;
	
	_arrival_state text;
    _arrival_time time;
	_arrival_id Integer;
	_arrival_terminal_id Integer;
	
BEGIN

    dateSelected := CURRENT_TIMESTAMP;
    
    if dateSelected IS NOT NULL THEN

        SELECT * INTO _rec FROM h_params LIMIT 1;

        DROP TABLE IF EXISTS h_arrival_punch CASCADE;

        CREATE TABLE h_arrival_punch as 
        SELECT pe.id,
            pe.emp_code,
            pe.first_name,
            pe.last_name,
            ephoto,
            pe.edepartment,
            pe.eposition,
            pe.area,
            '00:00:00'::time arrival_time,
            '0' arrival_state,
            1 arrival_id,
            1 arrival_terminal_id

        FROM employee_view pe WHERE 0=1;

        for _row_record in SELECT * FROM employee_view

        loop
			_daySelect := dateSelected::date;
			
            SELECT gapr.id, gapr.arrival_time::time, gapr.arrival_state, gapr.arrival_terminal_id into 
            _arrival_id, _arrival_time, _arrival_state, _arrival_terminal_id 

            FROM public."getArrivalPunchRecord"(_row_record.emp_code::text,_daySelect::date) gapr(
				id Integer,
				arrival_time time,
				arrival_state text,
				arrival_terminal_id Integer
			);

            INSERT INTO h_arrival_punch(
                id ,
                emp_code ,
                first_name ,
                last_name,
                ephoto,
                edepartment,
                eposition,
                area,
                arrival_time,
                arrival_state,
                arrival_id,
                arrival_terminal_id
            ) VALUES(
                _row_record.id,
                _row_record.emp_code,
                _row_record.first_name,
                _row_record.last_name,
                _row_record.ephoto,
                _row_record.edepartment,
                _row_record.eposition,
                _row_record.area,
                _arrival_time,
                _arrival_state,
                _arrival_id,
                _arrival_terminal_id
            );

        end loop;
        
        _status = CURRENT_TIMESTAMP;

    else
        _status = 'DATE_EMPTY';
    END IF;

    EXCEPTION WHEN unique_violation THEN
        		GET STACKED DIAGNOSTICS _status = PG_EXCEPTION_DETAIL;
				
	return next _status;
	
END
$$;

select * from public."doArrivalRefresh"('2021-02-18'::date) ;