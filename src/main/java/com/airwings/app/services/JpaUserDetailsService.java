package com.airwings.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.DAO.usuario.UsuarioDao;
import com.airwings.app.model.entity.usuario.Permiso;
import com.airwings.app.model.entity.usuario.Usuario;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = usuarioDao.findByUsername(username);
		if(u==null) throw new UsernameNotFoundException("Username no encontrado");
		System.out.println(u.getRol().getNombre());	//
		System.out.println(u.getUsername());			// ############ BORRAR!!!!!
		System.out.println(u.getContrasena());			//
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(u.getRol().getNombre())); 	//agregar nombre de rol a la lista  ROLE_admin
		for (Permiso permiso: u.getRol().getPermisos()) {
			authorities.add(new SimpleGrantedAuthority(permiso.getNombre())); 		//agregar permisos a la lista
		}
		
		return new User(u.getUsername(), u.getContrasena(), !u.getBloqueado(), true, true, true, authorities);
		}
	
}
