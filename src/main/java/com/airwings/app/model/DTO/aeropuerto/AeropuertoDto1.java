package com.airwings.app.model.DTO.aeropuerto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AeropuertoDto1 {

	@NotBlank
	private String nombre;
	@NotBlank
	@Pattern(regexp = "^[0-9]{3}-[A-za-z]{3}$",message = "formato: 999-AAA")
	private String codigo;
	@NotNull
	private Long paisId;
}
