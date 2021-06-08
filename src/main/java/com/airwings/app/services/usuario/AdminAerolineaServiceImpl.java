package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.airwings.app.model.DAO.usuario.AdminAerolineaDao;
import com.airwings.app.model.entity.usuario.AdminAerolinea;

public class AdminAerolineaServiceImpl implements AdminAerolineaService{

	@Autowired
	AdminAerolineaDao adminAerolDao;
	
	@Override
	public List<AdminAerolinea> findAll() {
		return adminAerolDao.findAll();
	}

	@Override
	public AdminAerolinea findById(Long id) {
		return adminAerolDao.findById(id).orElse(null);
	}

	@Override
	public AdminAerolinea save(AdminAerolinea a) {
		return adminAerolDao.save(a);
	}

	@Override
	public void deleteById(Long id) {
		adminAerolDao.deleteById(id);
	}

	@Override
	public Long getRegistroAdminAerop(Long ida, Long id) {
		return adminAerolDao.getIdRegistroAdminAerol(ida, id);
	}

}
