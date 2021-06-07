package com.airwings.app.services;

import java.util.List;

import com.airwings.app.model.DTO.aerolinea.AerolineaDto;
import com.airwings.app.model.entity.Aerolinea;

public interface AerolineaService {
	
	public List<Aerolinea> findAll();
	public Aerolinea findById(Long id);
	public Aerolinea save(Aerolinea a);
	public Aerolinea save(AerolineaDto a);
	public void deleteById(Long id);
}
