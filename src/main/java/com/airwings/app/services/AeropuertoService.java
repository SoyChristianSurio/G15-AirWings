package com.airwings.app.services;

import java.util.List;

import com.airwings.app.model.entity.Aeropuerto;

public interface AeropuertoService {
	
	public Aeropuerto findById(Long id);
	public List<Aeropuerto> findAll();
	public Aeropuerto save(Aeropuerto aeropuerto);
	public void deleteById(Long id);
}
