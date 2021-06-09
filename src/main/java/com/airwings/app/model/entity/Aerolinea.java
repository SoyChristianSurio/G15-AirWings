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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.airwings.app.model.DTO.aerolinea.AerolineaDto;
import com.airwings.app.model.entity.avion.Avion;
import com.airwings.app.model.entity.usuario.AdminAerolinea;

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
	
	@Temporal(TemporalType.DATE)
	private Date fechaFundacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aerolinea")
	private List<AdminAerolinea> admins;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aerolinea")
	private List<Avion> aviones;
	
	public AerolineaDto toAerolineaDto() {
		AerolineaDto a = new AerolineaDto();
		a.setId(id);
		a.setCodigo(codigo);
		a.setNombreCorto(nombreCorto);
		a.setNombreLargo(nombreLargo);
		a.setRepresentante(representante);
		a.setFechaFundacion(fechaFundacion);
		a.setPaisId(pais.getId());
		a.setCiudadId(ciudad.getId());
		return a;
	}
}
