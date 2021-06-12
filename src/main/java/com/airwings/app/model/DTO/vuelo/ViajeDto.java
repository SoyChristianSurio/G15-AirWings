package com.airwings.app.model.DTO.vuelo;

import java.util.List;

import lombok.Data;

@Data
public class ViajeDto {

	private Long id;
	
	private Double precio;
	private Long duracion;
	private List<Long> vuelos;
}
