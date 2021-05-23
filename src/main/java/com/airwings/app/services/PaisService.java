package com.airwings.app.services;

import java.util.Optional;

import com.airwings.app.model.entity.Pais;

public interface PaisService {
	public Iterable<Pais> findAll();
	public Optional<Pais> findById(Long id);
	public Pais save(Pais pais);
	public void deleteById(Long id);
}
