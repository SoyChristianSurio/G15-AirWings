package com.airwings.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.PaisDao;
import com.airwings.app.model.entity.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisDao paisDao;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Pais> findAll() {
		return paisDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pais findById(Long id) {
		return paisDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pais save(Pais pais) {
		return paisDao.save(pais);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		paisDao.deleteById(id);
	}

}
