package com.airwings.app.services.usuario;

import java.util.List;

import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.usuario.AdminAerolinea;

public interface AdminAerolineaService {
	
	public List<AdminAerolinea> findAll();
	public AdminAerolinea findById(Long id);
	public AdminAerolinea save(AdminAerolinea a);
	public void deleteById(Long id);
	public Long getRegistroAdminAerol(Long ida, Long id);
	public List<AdminAerolinea> findAllByAerolinea(Aerolinea a);
	
}
