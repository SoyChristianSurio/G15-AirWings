package com.airwings.app.model.DTO.vuelo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ViajeDto {

	private Long id;
	@NotBlank
	private String nombre;
	private Double precio;
	private Long duracion;
	
	
}
