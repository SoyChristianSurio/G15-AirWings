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