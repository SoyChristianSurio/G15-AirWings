package com.airwings.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airwings.app.model.DAO.AerolineaDao;
import com.airwings.app.model.DTO.aerolinea.AerolineaDto;
import com.airwings.app.model.entity.Aerolinea;

@Service
public class AerolineaServiceImpl implements AerolineaService {

	@Autowired
	AerolineaDao aerolDao;
	@Autowired
	PaisService paisService;
	@Autowired
	CiudadService ciudadService;
	
	@Override
	public List<Aerolinea> findAll() {
		return aerolDao.findAll();
	}

	@Override
	public Aerolinea findById(Long id) {
		return aerolDao.findById(id).orElse(null);
	}

	@Override
	public Aerolinea save(Aerolinea a) {
		return aerolDao.save(a);
	}

	@Override
	public void deleteById(Long id) {
		aerolDao.deleteById(id);		
	}

	@Override
	public Aerolinea save(AerolineaDto a) {
		Aerolinea ae =new Aerolinea();
		ae.setId(a.getId());
		ae.setCodigo(a.getCodigo());
		ae.setNombreLargo(a.getNombreLargo());
		ae.setNombreCorto(a.getNombreCorto());
		ae.setRepresentante(a.getRepresentante());
		ae.setFechaFundacion(a.getFechaFundacion());
		ae.setPais(paisService.findById(a.getPaisId()));
		ae.setCiudad(ciudadService.findById(a.getCiudadId()));
		return aerolDao.save(ae);
	}

}
