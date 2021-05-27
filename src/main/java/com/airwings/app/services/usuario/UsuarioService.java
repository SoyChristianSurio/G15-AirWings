package com.airwings.app.services.usuario;

import java.util.List;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario findByUsername(String username);
	public Usuario save(Usuario usuario);
	public Usuario registrar(UsuarioRegistrable usuario);
	public void deleteById(Long id);
	
}
