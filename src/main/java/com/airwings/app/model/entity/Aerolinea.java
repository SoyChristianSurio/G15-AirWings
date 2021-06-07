package com.airwings.app.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.airwings.app.model.entity.avion.Avion;

import lombok.Data;

@Entity
@Data
public class Aerolinea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private String nombreLargo;
	private String nombreCorto;
	private String representante;
	private Date fechaFundacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aerolinea")
	private List<Avion> aviones;
}
