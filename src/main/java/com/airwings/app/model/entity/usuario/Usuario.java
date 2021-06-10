package com.airwings.app.model.entity.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;

import lombok.Data;

@Entity
@Data
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Rol rol;
	private String username;
	private String contrasena;
	private String correo;
	private Integer contadorBloqueo;
	private Boolean bloqueado;
	private Boolean clienteNatural;
	private Boolean registroCompleto;
	
	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	private ClienteNatural natural;
	
	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	private ClienteEmpresa empresa;
	
	public UsuarioRegistrable getUsuarioRegistrable() {
		UsuarioRegistrable u =new UsuarioRegistrable();
		u.setId(id);
		u.setUsername(username);
		u.setPass(contrasena);
		u.setPassConfirm(contrasena);
		u.setPersona(clienteNatural);
		u.setEmail(correo);
		u.setRolId(rol.getId());
		
		return u;
	}
}
