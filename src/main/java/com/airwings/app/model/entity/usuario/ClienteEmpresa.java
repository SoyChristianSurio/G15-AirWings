package com.airwings.app.model.entity.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ClienteEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String telefonoFijo;
	private String telefonoMovil;
	private String nombre;
	private String nombreContacto;
	private String direccion;
	private String nit;
	private String nic;
	private String numeroViajero;
	private Double millas;
	private Double distanciaRecorrida;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
}
