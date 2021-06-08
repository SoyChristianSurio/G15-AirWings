package com.airwings.app.services.usuario;

import java.util.List;

import com.airwings.app.model.DTO.usuario.EmpresaAutoEdit;
import com.airwings.app.model.DTO.usuario.PersonaAutoEdit;
import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.ClienteEmpresa;
import com.airwings.app.model.entity.usuario.ClienteNatural;
import com.airwings.app.model.entity.usuario.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario findByUsername(String username);
	public Usuario save(Usuario usuario);
	public Usuario registrar(UsuarioRegistrable usuario);
	public PersonaAutoEdit getPersonaAutoEdit(ClienteNatural cn);
	public EmpresaAutoEdit getEmpresaAutoEdit(ClienteEmpresa ce);
	public void deleteById(Long id);
	public String savePersona(PersonaAutoEdit persona);
	public String saveEmpresa(EmpresaAutoEdit empresa);
	
	public List<Usuario> findAllRolAeropAdmin();
	public List<Usuario> findAllAdminNotOfAerop(Long id);
	public List<Usuario> findAllAdminNotOfAerol(Long id);
	public List<Usuario> findAllAdminOfAerop(Long id);
	public List<Usuario> findAllAdminOfAerol(Long id);
	public Usuario findUserAdminOfAerop(Long userId, Long aeropId);
	public Usuario findUserAdminOfAerol(Long userId, Long aeropId);
	
}
