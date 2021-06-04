package com.airwings.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.AeropuertoDao;
import com.airwings.app.model.entity.Aeropuerto;
@Service
public class AeropuertoServiceImpl implements AeropuertoService {

	@Autowired
	AeropuertoDao aeropuertoDao;
	
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
		aeropuertoDao.deleteById(id);
	}

}
