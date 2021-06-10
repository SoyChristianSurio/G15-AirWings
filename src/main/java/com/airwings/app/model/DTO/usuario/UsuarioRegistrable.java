package com.airwings.app.model.DTO.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioRegistrable {
	private Long id;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String pass;
	
	private String passConfirm;
	
	
	private Boolean persona;
	
	@Email(message = "escriba un correo válido")
	@NotBlank
	private String email;
	
	private Long rolId;
	
}
