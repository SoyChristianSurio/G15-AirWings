package com.airwings.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "asiento")
public class Asiento implements Serializable {	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name = "cantidad_asiento")
	private Integer cantidadAsiento;
	
	@Column(name = "precio_asiento",precision = 2)
	private Double precioAsiento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Avion avion;
}