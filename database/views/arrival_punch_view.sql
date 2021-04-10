DROP VIEW IF EXISTS h_arrival_punch_view CASCADE;
CREATE OR REPLACE VIEW h_arrival_punch_view AS

	SELECT public."doarrivalrefresh"();
	SELECT * FROM h_arrival_punch;
 
 select * FROM h_arrival_punch_view;