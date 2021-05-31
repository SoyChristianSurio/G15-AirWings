package com.airwings.app.model.DTO.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmpresaAutoEdit {
	
	private Long usuarioId;
	@NotBlank
	private String nombre;
	@NotBlank
	private String nombreContacto;
	@NotBlank
	private String direccion;
	@NotBlank
	private String nit;
	@NotBlank
	private String nic;
	@NotBlank
	private String numeroViajero;
	@NotBlank
	private String telefonoFijo;
	@NotBlank
	private String telefonoMovil;
	
}
