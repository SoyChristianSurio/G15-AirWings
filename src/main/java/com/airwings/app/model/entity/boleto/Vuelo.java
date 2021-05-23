package com.airwings.app.model.entity.boleto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.avion.Avion;

import lombok.Data;

@Entity
@Data
public class Vuelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private Date fechaDespegue;
	private Date fechaAterrizaje;
	private Date duracion;
	private Double distancia;
	private Double precio;
	
	@OneToOne
	private Aeropuerto origen;
	@OneToOne
	private Aeropuerto destino;	
	@ManyToOne
	private Avion avion;
	@ManyToOne
	private VueloEstado estado;
	@ManyToMany(mappedBy = "vuelos")
	private List<Viaje> viajes;
	
}
