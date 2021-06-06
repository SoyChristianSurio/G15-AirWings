package com.airwings.app.model.entity.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airwings.app.model.entity.Aeropuerto;

import lombok.Data;

@Entity
@Data
public class AdminAeropuerto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Aeropuerto aeropuerto;
	@ManyToOne
	private Usuario usuario;
	
}
