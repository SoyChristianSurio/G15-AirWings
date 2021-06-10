package com.airwings.app.model.DTO.aerolinea;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class AerolineaDto {
	private Long id;	
	@Pattern(regexp = "^[A-Za-z]{3}$",message = "Deben ser 3 letras")
	private String codigo;
	@NotBlank
	private String nombreLargo;
	@NotBlank
	@Size(max = 20, min = 2)
	private String nombreCorto;
	@NotBlank
	private String representante;
	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Debe seleccionar la fecha de fundación")
	private Date fechaFundacion;
	@NotNull(message = "Debe escoger un país")
	private Long paisId;
	@NotNull(message = "Debe escoger una ciudad")
	private Long ciudadId;
}
