//ALTER TABLE gustos_musicales add email varchar(90) unique not null;


//DROP TABLE tipo_musica_gustos_musicales;

INSERT INTO tipo_musica (nombre,email) VALUES('Jazz','patorma@yahoo.com');
INSERT INTO tipo_musica (nombre,email) VALUES('Pop','mevoya@hotmail.com');
INSERT INTO tipo_musica (nombre,email) VALUES('Rock','foca@gmail.com');
INSERT INTO tipo_musica (nombre,email) VALUES('Kpop','dragon@gmail.com');
INSERT INTO tipo_musica (nombre,email) VALUES('Kpop','hola@gmail.com');

/* Creamos algunos usuarioscon sus roles */
INSERT INTO usuarios (username, password ,enabled,nombre,apellido) VALUES ('patricio','$2a$10$Pg75KKTEtfrkAG8QczRa5e198RwKzWlG9uQaGY4LHPDKPr.2X8IAK',1,'Patricio','Contreras');
INSERT INTO usuarios (username, password ,enabled,nombre,apellido) VALUES ('admin','$2a$10$GGBsqpfvQzxeelKv0bgpjObafkfHwCu6Fjp06NZq33ecAPvTaSqfm',1,'John','Doe');
INSERT INTO usuarios (username, password ,enabled,nombre,apellido) VALUES ('raul','$2a$10$lOEjPK1VcxsAIATWaq6qf.KK/e2r/5i33LTCCoc2SzQZxyhL/Ndwm',1,'Raul','Duarte');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (3,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (3,2);
//INSERT INTO gusto_musical(usuario_id,id_tipo_musica,email) VALUES (1,2,'patorma@yahoo.com');
//INSERT INTO gusto_musical(usuario_id,id_tipo_musica,email) VALUES (2,2,'mevoya@hotmail.com');
//NSERT INTO gusto_musical(usuario_id,id_tipo_musica,email) VALUES (3,3,'foca@gmail.com');
