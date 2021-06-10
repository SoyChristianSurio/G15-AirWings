package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airwings.app.model.DAO.usuario.AdminAerolineaDao;
import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.usuario.AdminAerolinea;
import com.airwings.app.model.entity.usuario.Usuario;

@Service
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
	public Long getRegistroAdminAerol(Long ida, Long id) {
		return adminAerolDao.getIdRegistroAdminAerol(ida, id);
	}

	@Override
	public List<AdminAerolinea> findAllByAerolinea(Aerolinea a) {
		return adminAerolDao.findAllByAerolinea(a);
	}

	@Override
	public List<AdminAerolinea> findAllByUsuario(Usuario u) {
		return adminAerolDao.findAllByUsuario(u);
	}

}
