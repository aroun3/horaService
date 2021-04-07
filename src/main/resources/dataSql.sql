
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

/*  Initailisation des roles */
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');