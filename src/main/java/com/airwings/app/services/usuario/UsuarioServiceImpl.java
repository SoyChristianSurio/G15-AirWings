package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.usuario.RolDao;
import com.airwings.app.model.DAO.usuario.UsuarioDao;
import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	RolDao rolDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);		
	}

	@Override
	@Transactional
	public Usuario registrar(UsuarioRegistrable usuario) {
		Usuario u = new Usuario();
		u.setContadorBloqueo(0);
		u.setBloqueado(false);
		u.setUsername(usuario.getUsername());
		u.setClienteNatural(usuario.getPersona());
		u.setContraseña(passEncoder.encode(usuario.getPass()));
		u.setCorreo(usuario.getEmail());
		u.setRegistroCompleto(false);
		u.setRol(rolDao.findByNombre("ROLE_user"));
		
		return usuarioDao.save(u);
	}

}
