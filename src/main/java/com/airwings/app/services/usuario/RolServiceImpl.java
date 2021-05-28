package com.airwings.app.services.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import com.airwings.app.model.DAO.usuario.RolDao;
import com.airwings.app.model.entity.usuario.Rol;

public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolDao rolDao;
	
	@Override
	public Rol findByNombre(String nombre) {
		return rolDao.findByNombre(nombre);
	}

}
