package com.airwings.app.model.entity.boleto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@ManyToOne
	private Aerolinea aerolinea;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
	private List<Vuelo> vuelos;
	
	public ViajeDto getViajeDto() {
		ViajeDto v = new ViajeDto();
		v.setId(id);
		v.setPrecio(precio);
		v.setDuracion(duracion);
		for(Vuelo vu:vuelos) {
			v.getVuelos().add(vu.getId());
		}
		return v;
	}
}





