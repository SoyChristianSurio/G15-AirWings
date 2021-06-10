package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airwings.app.model.DAO.usuario.RolDao;
import com.airwings.app.model.entity.usuario.Rol;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolDao rolDao;
	
	@Override
	public Rol findByNombre(String nombre) {
		return rolDao.findByNombre(nombre);
	}

	@Override
	public List<Rol> findAll() {
		return rolDao.findAll();
	}

}
