package com.airwings.app.model.DTO.aeropuerto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AeropuertoDto2 {

	private Long id;
	@NotBlank
	private String nombre;
	@NotBlank
	private String telefono;
	@NotBlank
	private String nombreResponsable;
	@Pattern(regexp = "^[0-9]{3}-[A-za-z]{3}$",message = "formato: 999-AAA")
	private String codigo;
	@NotNull
	private Integer numeroBahia;
	@NotNull
	private Integer capacidad;
	@NotNull
	private Long paisId;
	@NotNull
	private Long ciudadId;
}
