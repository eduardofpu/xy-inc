CREATE SCHEMA IF NOT EXISTS "xy-inc";


SET SEARCH_PATH TO 'xy-inc';

CREATE TABLE PUBLIC.tb_pois_interest
(
    id BIGSERIAL    NOT NULL CONSTRAINT pois_pkey PRIMARY KEY,
    name varchar(45) NOT NULL,
	coordinatedx Integer NOT NULL,
	coordinatedy Integer NOT NULL
	);
CREATE INDEX tb_pois_interest_id_idx
  ON PUBLIC.tb_pois_interest (id);

INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (27,12,'Lanchonete');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (31,18,'Posto');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (15,12,'Joalheria');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (19,21,'Floricultura');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (12,8,'Pub');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (23,6,'Supermercado');
INSERT INTO PUBLIC.tb_pois_interest (coordinatedx, coordinatedy,name) VALUES (28,2,'Churrascaria');

