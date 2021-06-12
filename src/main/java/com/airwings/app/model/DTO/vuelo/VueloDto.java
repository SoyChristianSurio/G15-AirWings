package com.airwings.app.model.DTO.vuelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class VueloDto {
	
	private Long id;
	
	@Pattern(regexp = "^[0-9]{3}$", message = "deben ser 3 números")
	private String codigo;
	@NotBlank(message = "obligatorio")
	private String fechaDespegue;
	@NotBlank(message = "obligatorio")
	private String horaDespegue;
	
	@NotBlank(message = "obligatorio")
	private String fechaAterrizaje;
	@NotBlank(message = "obligatorio")
	private String horaAterrizaje;
	
	
	@NotNull
	private Double distancia;
	
	@NotNull(message = "estabelzca precio")
	private Double precio;
	
	@NotNull(message = "obligatorio")
	private Long aeropOrigenId;
	@NotNull(message = "obligatorio")
	private Long aeropDestinoId;
	//@NotNull(message = "obligatorio")
	private Long avionId;
	
	private Long viaje;
}
