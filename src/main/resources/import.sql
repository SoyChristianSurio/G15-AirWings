insert into public.rol (nombre) values('ROLE_admin');
insert into public.rol (nombre) values('ROLE_user');
insert into public.rol (nombre) values('ROLE_aerolinea_admin');
insert into public.rol (nombre) values('ROLE_aeropuerto_admin');

insert into pais (nombre, codigo) values('El Salvador','SV');
insert into pais (nombre, codigo) values('España','ES');
insert into pais (nombre, codigo) values('Estados Unidos','US');

insert into ciudad (nombre, pais_id) values('San Salvador',1 );
insert into ciudad (nombre, pais_id) values('Ilopango',1 );
insert into ciudad (nombre, pais_id) values('Washingtong DC',3 );
insert into ciudad (nombre, pais_id) values('Ibiza',2 );
insert into ciudad (nombre, pais_id) values('Santander',2 );
insert into ciudad (nombre, pais_id) values('Madrid', 2);
insert into ciudad (nombre, pais_id) values('Barcelona', 2);
insert into ciudad (nombre, pais_id) values('Alicante',2 );
insert into ciudad (nombre, pais_id) values('Valencia', 2);
insert into ciudad (nombre, pais_id) values('Houston Texas',3 );
insert into ciudad (nombre, pais_id) values('Los Angeles',3 );
insert into ciudad (nombre, pais_id) values('Nueva York',3 );

insert into tipo_documento (nombre, descripcion) values ('DUI','Documento único de identidad');
insert into tipo_documento (nombre, descripcion) values ('NIT','Numero de identidad Tributaria');
insert into tipo_documento (nombre, descripcion) values ('NIC','Numero de identificación de Contrato');
     
insert into estado_civil (nombre) values ('Soltero');
insert into estado_civil (nombre) values ('Casado');

insert into estado_vuelo (nombre) values ('Disponible');
insert into estado_vuelo (nombre) values ('Lleno');
insert into estado_vuelo (nombre) values ('Finalizado');
insert into estado_vuelo (nombre) values ('Cancelado');

insert into clase (nombre) values('Económica');
insert into clase (nombre) values('Turista');
insert into clase (nombre) values('Ejecutiva');
insert into clase (nombre) values('Primera Clase');

insert into social_network (nombre) values('Facebook');
insert into social_network (nombre) values('Twitter');
insert into social_network (nombre) values('Instagram');
insert into social_network (nombre) values('youtube');

insert into tipo_avion (nombre) values ('comercial');
insert into tipo_avion (nombre) values ('carga');
insert into tipo_avion (nombre) values ('Turístico');
insert into tipo_avion (nombre) values ('militar');



Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('admin', 1, '$2a$10$tLizco88QqRv80R/l1IHZubLLhS8rKlXRoSbxg0MJWUU784TuY0f6','Admin@mail.com',NULL,false);

Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('ap1', 4, '$2a$10$cBzQi.jtLwFfFej2EcUJRe6xkEiBNg8G6.0gaeIZPjNLjs53BSWwW','aerop1@mail.com',NULL,false);
Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('ap2', 4, '$2a$10$BeU124NpNdqTbevRip2oJegwqYiovH7UMG9N9oElnv0LyKMMkdk2C','aerop2@mail.com',NULL,false);
Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('ap3', 4, '$2a$10$43buC3qn.0Y8nFRp8FgJL.09IeFZn5dvRNBxhnrbxifxmqHtHZ1VG','aerop3@mail.com',NULL,false);

Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('al1', 3, '$2a$10$PbD3OODWpcSLT/fEgpBp2ewPT.V1UyrEgq1cKlgD2lBD8gfJLsHDW','aerol1@mail.com',NULL,false);
Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('al2', 3, '$2a$10$jFOIUVg7qfSrvhkuTPrzb.KPr5eyAU.SNFKD92lrNJwuHNnw9axsS','aerol2@mail.com',NULL,false);
Insert into usuario (username, rol_id, contrasena, correo, cliente_natural, bloqueado) values ('al3', 3, '$2a$10$wzZa6oRsBQfTjFEQkCke1OpUPdtoLYVhaeIQyKb3SfKumLEFfVl4O','aerol3@mail.com',NULL,false);



Insert into aeropuerto (capacidad, codigo, nombre, nombre_responsable, numero_bahia, telefono, ciudad_id)	VALUES (2, '123-asd', 'Romero Airport', 'Jaime Eseman Escool', 2, '78784545', 1);
Insert into aeropuerto (capacidad, codigo, nombre, nombre_responsable, numero_bahia, telefono, ciudad_id)	VALUES (5, '123-IBZ', 'Ibiza International Airport', 'Pancho Pantera', 4, '64754545', 4);
Insert into aeropuerto (capacidad, codigo, nombre, nombre_responsable, numero_bahia, telefono, ciudad_id)	VALUES (5, '123-WSH', 'Wachinton Airport', 'Atahualpa Yupanqui', 4, '64754545', 3);

Insert into aerolinea (codigo, fecha_fundacion, nombre_corto, nombre_largo, representante, ciudad_id, pais_id)	VALUES ('AQO', '2021-06-04', 'QueOnda', 'Aerolinea Que Onda', 'Official Gerrero', 5, 2);
Insert into aerolinea (codigo, fecha_fundacion, nombre_corto, nombre_largo, representante, ciudad_id, pais_id)	VALUES ('YVI', '2019-02-28', 'Voa Airline', 'Sha me Vo Airlines', 'Nombre Genérico', 2, 2);

Insert into admin_aeropuerto (aeropuerto_id, usuario_id) VALUES (1, 3);
Insert into admin_aerolinea (aerolinea_id, usuario_id) VALUES (1, 5);

INSERT INTO avion(codigo, marca, modelo, aerolinea_id, tipo_id)	VALUES ('AQO-121', 'mercedez', 'Boeing-777', 1, 1);
INSERT INTO avion(codigo, marca, modelo, aerolinea_id, tipo_id)	VALUES ('AQO-131', 'FlashAir', 'Concord-D', 1, 1);
INSERT INTO avion(codigo, marca, modelo, aerolinea_id, tipo_id)	VALUES ('AQO-141', 'CAT', 'Pajaro-D-A0', 2, 1);

INSERT INTO vuelo(codigo, distancia, duracion, fecha_aterrizaje, fecha_despegue, precio, avion_id, destino_id, estado_id, origen_id) VALUES ('', 1234, 61, '2021-06-12 00:21:00', '2021-06-11 23:20:00', 75, 3, 3, 1, 1);
INSERT INTO vuelo(codigo, distancia, duracion, fecha_aterrizaje, fecha_despegue, precio, avion_id, destino_id, estado_id, origen_id) VALUES ('', 1234, 60, '2021-06-12 01:21:00' ,'2021-06-12 00:21:00', 85, 2, 2, 1, 3);
INSERT INTO vuelo(codigo, distancia, duracion, fecha_aterrizaje, fecha_despegue, precio, avion_id, destino_id, estado_id, origen_id) VALUES ('', 1234, 60, '2021-06-12 01:21:00' ,'2021-06-12 00:21:00', 85, 3, 2, 1, 3);



create or replace function allRolAeropAdmin () returns table (	id bigint, bloqueado boolean, cliente_natural boolean, contador_bloqueo integer, contrasena character varying(255),	correo varchar(255), registro_completo boolean,	username varchar(255), rol_id bigint) language plpgsql as $$ begin	return query select u.* from usuario u join rol r on (u.rol_id=r.id) where r.nombre='ROLE_aeropuerto_admin'; end;$$;

create or replace function allAdminNotOfAerop (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aeropuerto_admin' and u.id not in (select adp.usuario_id from admin_aeropuerto adp inner join aeropuerto ap on adp.aeropuerto_id = ap.id where ap.id = $1 );end;$$
create or replace function allAdminNotOfAerol (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aerolinea_admin' and u.id not in  (select adl.usuario_id from admin_aerolinea adl inner join aerolinea  al on adl.aerolinea_id = al.id where al.id = $1 );end;$$

create or replace function allAdminOfAerop (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint ) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aeropuerto_admin' and u.id in (select adp.usuario_id from admin_aeropuerto adp inner join aeropuerto ap on adp.aeropuerto_id = ap.id where ap.id = $1 );end;$$
create or replace function allAdminOfAerol (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint ) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aerolinea_admin' and u.id in (select adl.usuario_id from admin_aerolinea adl inner join aerolinea al on adl.aerolinea_id = al.id where al.id = $1 );end;$$

create or replace function findUserAdminOfAerop (bigint,bigint) returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select * from usuario where usuario.id in (select adp.usuario_id from admin_aeropuerto adp where adp.usuario_id = $1 and adp.aeropuerto_id= $2);end;$$
create or replace function findUserAdminOfAerol (bigint,bigint) returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contrasena character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select * from usuario where usuario.id in (select adl.usuario_id from admin_aerolinea  adl where adl.usuario_id = $1 and adl.aerolinea_id= $2);end;$$