package com.airwings.app.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.airwings.app.model.DTO.aeropuerto.AeropuertoDto2;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;

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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aeropuerto")
	private List<AdminAeropuerto> admins;		
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;
	
	public AeropuertoDto2 toAeropuertoDto2() {
		AeropuertoDto2 a = new AeropuertoDto2();
		a.setId(id);
		a.setNombre(nombre);
		a.setTelefono(telefono);
		a.setNombreResponsable(nombreResponsable);
		a.setCodigo(codigo);
		a.setNumeroBahia(numeroBahia);
		a.setCapacidad(capacidad);
		a.setPaisId(this.ciudad.getPais().getId());
		a.setCiudadId(this.ciudad.getId());
		return a;
	}
}
