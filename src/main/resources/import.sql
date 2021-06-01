insert into public.rol (nombre) values('ROLE_admin');
insert into public.rol (nombre) values('ROLE_user');
insert into public.rol (nombre) values('ROLE_aerolinea_admin');
insert into public.rol (nombre) values('ROLE_aeropuerto_admin');

insert into tipo_documento (nombre, descripcion) values ('DUI','Documento único de identidad');
insert into tipo_documento (nombre, descripcion) values ('NIT','Numero de identidad Tributaria');
insert into tipo_documento (nombre, descripcion) values ('NIC','Numero de identificación de Contrato');

insert into estado_civil (nombre) values ('Soltero');
insert into estado_civil (nombre) values ('Casado');

Insert into usuario (username, rol_id, contraseña, correo, cliente_natural, bloqueado) values ('admin', 1, '$2a$10$tLizco88QqRv80R/l1IHZubLLhS8rKlXRoSbxg0MJWUU784TuY0f6','Admin@mail.com',NULL,false);