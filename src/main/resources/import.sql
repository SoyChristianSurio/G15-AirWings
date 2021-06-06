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

Insert into usuario (username, rol_id, contraseña, correo, cliente_natural, bloqueado) values ('admin', 1, '$2a$10$tLizco88QqRv80R/l1IHZubLLhS8rKlXRoSbxg0MJWUU784TuY0f6','Admin@mail.com',NULL,false);
Insert into usuario (username, rol_id, contraseña, correo, cliente_natural, bloqueado) values ('ap1', 4, '$2a$10$cBzQi.jtLwFfFej2EcUJRe6xkEiBNg8G6.0gaeIZPjNLjs53BSWwW','aerop1@mail.com',NULL,false);
Insert into usuario (username, rol_id, contraseña, correo, cliente_natural, bloqueado) values ('ap2', 4, '$2a$10$BeU124NpNdqTbevRip2oJegwqYiovH7UMG9N9oElnv0LyKMMkdk2C','aerop2@mail.com',NULL,false);
Insert into usuario (username, rol_id, contraseña, correo, cliente_natural, bloqueado) values ('ap3', 4, '$2a$10$43buC3qn.0Y8nFRp8FgJL.09IeFZn5dvRNBxhnrbxifxmqHtHZ1VG','aerop3@mail.com',NULL,false);

Insert into aeropuerto (capacidad, codigo, nombre, nombre_responsable, numero_bahia, telefono, ciudad_id)	VALUES (2, '123-asd', 'Romero Airport', 'Jaime Eseman Escool', 2, '78784545', 1);

Insert into admin_aeropuerto (aeropuerto_id, usuario_id) VALUES (1, 3);

create or replace function allRolAeropAdmin () returns table (	id bigint, bloqueado boolean, cliente_natural boolean, contador_bloqueo integer, contraseña character varying(255),	correo varchar(255), registro_completo boolean,	username varchar(255), rol_id bigint) language plpgsql as $$ begin	return query select u.* from usuario u join rol r on (u.rol_id=r.id) where r.nombre='ROLE_aeropuerto_admin'; end;$$;
create or replace function allAdminNotOfAerop (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contraseña character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aeropuerto_admin' and u.id not in (select adp.usuario_id from admin_aeropuerto adp inner join aeropuerto ap on adp.aeropuerto_id = ap.id where ap.id = $1 );end;$$
create or replace function allAdminOfAerop (bigint)	returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contraseña character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint ) language plpgsql as $$ begin return query select u.* from usuario u inner join rol r on u.rol_id = r.id where r.nombre='ROLE_aeropuerto_admin' and u.id in (select adp.usuario_id from admin_aeropuerto adp inner join aeropuerto ap on adp.aeropuerto_id = ap.id where ap.id = $1 );end;$$
create or replace function findUserAdminOfAerop (bigint,bigint) returns table (id bigint,bloqueado boolean,cliente_natural boolean,contador_bloqueo integer,contraseña character varying(255),correo varchar(255),registro_completo boolean,username varchar(255),rol_id bigint) language plpgsql as $$ begin return query select * from usuario where usuario.id in (select adp.usuario_id from admin_aeropuerto adp where adp.usuario_id = $1 and adp.aeropuerto_id= $2);end;$$
