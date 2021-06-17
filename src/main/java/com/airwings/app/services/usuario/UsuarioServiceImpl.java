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
import com.airwings.app.model.entity.usuario.AdminAerolinea;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;
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
	AdminAerolineaService admAerolService;
	@Autowired
	AdminAeropuertoService admAeropService;
	@Autowired
	RolDao rolDao;
	public static final int MAX_FAILED_ATTEMPTS = 3;
	
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
		u.setContrasena(passEncoder.encode(usuario.getPass()));
		u.setCorreo(usuario.getEmail());
		u.setRegistroCompleto(false);
		u.setRol(rolDao.findByNombre("ROLE_user"));
		
		return usuarioDao.save(u);
	}
	
	
	@Override
	@Transactional
	public Usuario save(UsuarioRegistrable usuario) {
		Usuario u = usuarioDao.findById(usuario.getId()).orElse(null);
		if(u==null) return null;
		
		u.setUsername(usuario.getUsername());
		u.setClienteNatural(usuario.getPersona());
		u.setCorreo(usuario.getEmail());
		System.out.println("rol actual: "+u.getRol().getId());
		System.out.println("rol nuevo: "+usuario.getRolId());
		if(u.getRol().getId()!=usuario.getRolId()) {
			System.out.println("nombre: "+u.getRol().getNombre());
			if(u.getRol().getId() == (long)3) {
				System.out.println("cambiando de aerol admin");
				for(AdminAerolinea adm: admAerolService.findAllByUsuario(u)) {
					admAerolService.deleteById(adm.getId());
				}
			}
			if(u.getRol().getId() == (long)4) {
				System.out.println("cambiando de aerop admin");
				for(AdminAeropuerto adm: admAeropService.findAllByUsuario(u)) {
					admAeropService.deleteById(adm.getId());
				}
			}
		}
		u.setRol(rolDao.findById(usuario.getRolId()).orElse(null));
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
	public List<Usuario> findAllRolAeropAdmin() {return usuarioDao.allRolAeropAdmin();}

	@Override
	public List<Usuario> findAllAdminNotOfAerop(Long id) {return usuarioDao.allAdminNotOfAerop(id);}
	@Override
	public List<Usuario> findAllAdminNotOfAerol(Long id) {return usuarioDao.allAdminNotOfAerol(id);}
	
	@Override
	public List<Usuario> findAllAdminOfAerop(Long id) {return usuarioDao.allAdminOfAerop(id);}
	@Override
	public List<Usuario> findAllAdminOfAerol(Long id) {return usuarioDao.allAdminOfAerol(id);}

	@Override
	public Usuario findUserAdminOfAerop(Long userId, Long aeropId) {return usuarioDao.findUserAdminOfAerop(userId, aeropId);}
	@Override
	public Usuario findUserAdminOfAerol(Long userId, Long aeropId) {return usuarioDao.findUserAdminOfAerol(userId, aeropId);}

	@Override
	public void updateContadorBloqueo(int intentosFallidos, String username) {
		usuarioDao.updateContadorBloqueo(intentosFallidos, username);		
	}
	@Override
	public void increaseFailedAttempts(Usuario user) {
        int newFailAttempts = user.getContadorBloqueo() + 1;
        usuarioDao.updateContadorBloqueo(newFailAttempts, user.getUsername());
    }
	
	@Override
    public void resetFailedAttempts(String username) {
    	usuarioDao.updateContadorBloqueo(0, username);
    }
	
	@Override
    public void lock(Usuario user) {
        user.setBloqueado(true);         
        usuarioDao.save(user);
    }
	
	
}
