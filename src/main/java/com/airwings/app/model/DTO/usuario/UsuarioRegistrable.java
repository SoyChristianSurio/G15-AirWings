package com.airwings.app.model.DTO.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioRegistrable {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String pass;
	
	private String passConfirm;
	
	@NotNull(message = "debe seleccionar uno")
	private Boolean persona;
	
	@Email(message = "escriba un correo válido")
	@NotBlank
	private String email;
	
}
