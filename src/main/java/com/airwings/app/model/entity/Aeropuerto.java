package com.airwings.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Aeropuerto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nombre;
	private String telefono;
	private String nombreResponsable;
	private String codigo;
	private Integer numeroBahia;
	private Integer capacidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;
}
