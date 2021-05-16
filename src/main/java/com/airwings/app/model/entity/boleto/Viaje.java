package com.airwings.app.model.entity.boleto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double precio;
	private Date duracion;
	private Integer escalas;
	
	@JoinTable(
		name = "viaje_vuelo",
		joinColumns = @JoinColumn(name="fk_viaje", nullable = false),
		inverseJoinColumns = @JoinColumn(name="fk_vuelo", nullable = false)
	)
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Vuelo> vuelos;
}





