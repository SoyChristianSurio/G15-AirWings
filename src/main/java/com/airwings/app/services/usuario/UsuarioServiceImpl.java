package com.airwings.app.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.usuario.ClienteNaturalDao;
import com.airwings.app.model.DAO.usuario.EstadoCivilDao;
import com.airwings.app.model.DAO.usuario.RolDao;
import com.airwings.app.model.DAO.usuario.TipoDocumentoDao;
import com.airwings.app.model.DAO.usuario.UsuarioDao;
import com.airwings.app.model.DTO.usuario.EmpresaAutoEdit;
import com.airwings.app.model.DTO.usuario.PersonaAutoEdit;
import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.ClienteEmpresa;
import com.airwings.app.model.entity.usuario.ClienteNatural;
import com.airwings.app.model.entity.usuario.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	UsuarioDao usuarioDao;
	@Autowired
	ClienteNaturalDao clienteNaturalDao;
	@Autowired
	EstadoCivilDao ecDao;
	@Autowired
	TipoDocumentoDao tdDao;
	
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

	@Override
	@Transactional(readOnly = true)
	public PersonaAutoEdit getPersonaAutoEdit(ClienteNatural cn) {
		PersonaAutoEdit p = new PersonaAutoEdit();
		p.setUsuarioId(cn.getUsuario().getId());
		p.setPNombre(cn.getPNombre());
		p.setSNombre(cn.getSNombre());
		p.setPApellido(cn.getSApellido());
		p.setSApellido(cn.getSApellido());
		p.setFechaNacimiento(cn.getFechaNacimiento());
		p.setEstadoCivilId(cn.getEstadoCivil().getId());
		p.setDocumentoId(cn.getDocumento().getId());
		p.setNumeroDocumento(cn.getNumeroDocumento());
		p.setTelefonoFijo(cn.getTelefonoFijo());
		p.setTelefonoMovil(cn.getTelefonoMovil());
		p.setSexo(cn.getSexo());
		p.setNumViajero(cn.getNumViajero());
		return p;
	}

	@Override
	@Transactional
	public String savePersona(PersonaAutoEdit persona) {
		ClienteNatural cn;
		Usuario u = usuarioDao.findById(persona.getUsuarioId()).orElse(null);
		if(u.getNatural()==null) {
			cn = new ClienteNatural();
			cn.setDistanciaRecorrida(0.0);
			cn.setMillas(0.0);
			u.setRegistroCompleto(true);
			usuarioDao.save(u);
		}else {
			cn = u.getNatural();
		}
		
		cn.setPNombre(persona.getPNombre());
		cn.setSNombre(persona.getSNombre());
		cn.setPApellido(persona.getSApellido());
		cn.setSApellido(persona.getSApellido());
		cn.setFechaNacimiento(persona.getFechaNacimiento());
		cn.setEstadoCivil(ecDao.findById(persona.getEstadoCivilId()).orElse(null));
		cn.setDocumento(tdDao.findById(persona.getDocumentoId()).orElse(null));
		cn.setNumeroDocumento(persona.getNumeroDocumento());
		cn.setTelefonoFijo(persona.getTelefonoFijo());
		cn.setTelefonoMovil(persona.getTelefonoMovil());
		cn.setSexo(persona.getSexo());
		cn.setNumViajero(persona.getNumViajero());
		cn.setUsuario(u);
		
		clienteNaturalDao.save(cn);
		
		return "Los datos del usuario '"+u.getUsername()+"' han sido guardados";
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaAutoEdit getEmpresaAutoEdit(ClienteEmpresa ce) {
		EmpresaAutoEdit e = new EmpresaAutoEdit();
		e.setUsuarioId(ce.getUsuario().getId());
		e.setNic(ce.getNic());
		e.setNit(ce.getNit());
		e.setTelefonoFijo(ce.getTelefonoFijo());
		e.setTelefonoMovil(ce.getTelefonoMovil());
		e.setNombre(ce.getNombre());
		e.setNombreContacto(ce.getNombreContacto());
		e.setNumeroViajero(ce.getNumeroViajero());
		e.setDireccion(ce.getDireccion());
		
		return e;
	}

	@Override
	public String saveEmpresa(EmpresaAutoEdit empresa) {
		return null;
	}

	@Override
	public List<Usuario> findAllRolAeropAdmin() {
		return usuarioDao.allRolAeropAdmin();
	}

	@Override
	public List<Usuario> findAllAdminNotOfAerop(Long id) {
		return usuarioDao.allAdminNotOfAerop(id);
	}

	@Override
	public List<Usuario> findAllAdminOfAerop(Long id) {
		return usuarioDao.allAdminOfAerop(id);
	}

	@Override
	public Usuario findUserAdminOfAerop(Long userId, Long aeropId) {
		return usuarioDao.findUserAdminOfAerop(userId, aeropId);
	}
	
}
