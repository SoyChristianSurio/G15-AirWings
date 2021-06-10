package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.airwings.app.model.entity.usuario.Rol;

@Service
public interface RolService {
	
	public Rol findByNombre(String nombre);

	public List<Rol> findAll();
	
}
