package com.airwings.app.model.entity.boleto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airwings.app.model.entity.avion.Clase;
import com.airwings.app.model.entity.usuario.Usuario;

import lombok.Data;

@Entity
@Data
public class Boleto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private Double precio;
	private Integer cantidadMaletas;
	private Clase clase;
	@ManyToOne
	private Viaje viaje;
	
	@ManyToOne
	private Usuario cliente;
}
