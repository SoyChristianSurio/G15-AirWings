package com.airwings.app.services;

import java.util.Optional;

import com.airwings.app.model.entity.Ciudad;

public interface CiudadService {
	public Iterable<Ciudad> findAll();
	public Optional<Ciudad> findById(Long id);
	public Ciudad save(Ciudad ciudad);
	public void deleteById(Long id);
}
