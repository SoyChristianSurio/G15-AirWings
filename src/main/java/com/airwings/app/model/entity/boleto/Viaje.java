package com.airwings.app.model.entity.boleto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airwings.app.model.DTO.vuelo.ViajeDto;
import com.airwings.app.model.entity.Aerolinea;

import lombok.Data;

@Entity
@Data
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double precio;
	private Long duracion;
	private Integer escalas;
	@ManyToOne(fetch = FetchType.LAZY)
	private Vuelo vueloOrigen;
	@ManyToOne(fetch = FetchType.LAZY)
	private Vuelo vueloDestino;
	
	@ManyToOne
	private Aerolinea aerolinea;
	
	public ViajeDto getViajeDto() {
		ViajeDto v = new ViajeDto();
		v.setId(id);
		v.setPrecio(precio);
		v.setDuracion(duracion);
		return v;
	}
}





