package com.airwings.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.CiudadDao;
import com.airwings.app.model.entity.Ciudad;

@Service
public class CiudadServiceImpl implements CiudadService{

	@Autowired
	private CiudadDao ciudadDao;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Ciudad> findAll() {
		return ciudadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ciudad findById(Long id) {
		return ciudadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ciudad save(Ciudad ciudad) {
		return ciudadDao.save(ciudad);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		ciudadDao.deleteById(id);
	}

}
