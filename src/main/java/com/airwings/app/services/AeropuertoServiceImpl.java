package com.airwings.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.AeropuertoDao;
import com.airwings.app.model.DTO.aeropuerto.AeropuertoDto2;
import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;
import com.airwings.app.services.usuario.AdminAeropuertoService;
@Service
public class AeropuertoServiceImpl implements AeropuertoService {

	@Autowired
	AeropuertoDao aeropuertoDao;
	@Autowired
	CiudadService ciudadService;
	@Autowired
	AdminAeropuertoService adminAeropService;
	
	@Override
	@Transactional(readOnly = true)
	public Aeropuerto findById(Long id) {
		return aeropuertoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aeropuerto> findAll() {
		return aeropuertoDao.findAll();
	}

	@Override
	@Transactional
	public Aeropuerto save(Aeropuerto aeropuerto) {
		return aeropuertoDao.save(aeropuerto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		Aeropuerto aerop = aeropuertoDao.findById(id).orElse(null); 
		for(AdminAeropuerto adm: adminAeropService.findAllByAeropuerto(aerop)) {
			adminAeropService.deleteById(adm.getId());
		}
		aeropuertoDao.deleteById(id);
	}

	@Override
	public Aeropuerto save(AeropuertoDto2 aerop) {
		Aeropuerto a = new Aeropuerto();
		a.setId(aerop.getId());
		a.setNombre(aerop.getNombre());
		a.setTelefono(aerop.getTelefono());
		a.setNombreResponsable(aerop.getNombreResponsable());
		a.setCodigo(aerop.getCodigo());
		a.setNumeroBahia(aerop.getNumeroBahia());
		a.setCapacidad(aerop.getCapacidad());
		a.setCiudad(ciudadService.findById(aerop.getCiudadId()));
		a.setAdmins(new ArrayList<AdminAeropuerto>());
		return aeropuertoDao.save(a);
	}

}
