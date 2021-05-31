package com.airwings.app.model.DTO.usuario;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PersonaAutoEdit {
	private Long usuarioId;
	
	@NotBlank
	private String pNombre;
	private String sNombre;
	@NotBlank
	private String pApellido;
	private String sApellido;
	@NotNull
	private Long documentoId;
	@NotBlank
	private String numeroDocumento;
	private Long estadoCivilId;
	private String sexo;
	
	
	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	@NotBlank
	private String numViajero;
	private String telefonoFijo;
	private String telefonoMovil;
	
}
