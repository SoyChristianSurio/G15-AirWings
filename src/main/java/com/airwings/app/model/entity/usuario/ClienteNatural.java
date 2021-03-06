package com.airwings.app.model.entity.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class ClienteNatural implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String telefonoFijo;
	private String telefonoMovil;
	@NotBlank
	private String pNombre;
	private String sNombre;
	private String pApellido;
	private String sApellido;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String sexo;
	@ManyToOne
	private EstadoCivil estadoCivil;
	private String numViajero;
	private Double millas;
	private Double distanciaRecorrida;
	
	@ManyToOne
	private TipoDocumento documento;
	private String numeroDocumento; 
	
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
}
