package com.airwings.app.services;

import com.airwings.app.model.entity.Ciudad;

public interface CiudadService {
	
	public Iterable<Ciudad> findAll();
	public Ciudad findById(Long id);
	public Ciudad save(Ciudad ciudad);
	public void deleteById(Long id);
}
