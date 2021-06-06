package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airwings.app.model.DAO.usuario.AdminAeropuertoDao;
import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;

@Service
public class AdminAeropuertoServiceImpl implements AdminAeropuertoService {

	@Autowired
	AdminAeropuertoDao adminAeropDao;

	@Override
	public Long getRegistroAdminAerop(Long userId, Long aeropId) {
		return adminAeropDao.getIdRegistroAdminAerop(userId, aeropId);
	}

	@Override
	public void deleteById(Long id) {
		adminAeropDao.deleteById(id);		
	}

	@Override
	public AdminAeropuerto save(AdminAeropuerto registro) {
		return adminAeropDao.save(registro);
	}

	@Override
	public List<AdminAeropuerto> findAllByAeropuerto(Aeropuerto aerop){
		return adminAeropDao.findAllByAeropuerto(aerop);
	}

}
