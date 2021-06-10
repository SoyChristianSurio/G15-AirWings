package com.airwings.app.services.usuario;

import java.util.List;

import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;
import com.airwings.app.model.entity.usuario.Usuario;

public interface AdminAeropuertoService {

	public Long getRegistroAdminAerop(Long userId, Long aeropId);
	public void deleteById(Long id);
	public AdminAeropuerto save(AdminAeropuerto registro);
	public List<AdminAeropuerto> findAllByAeropuerto(Aeropuerto aerop);
	public List<AdminAeropuerto> findAllByUsuario(Usuario u);
}
