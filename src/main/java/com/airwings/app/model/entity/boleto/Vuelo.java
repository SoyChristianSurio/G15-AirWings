package com.airwings.app.model.entity.boleto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.airwings.app.model.DTO.vuelo.VueloDto;
import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.avion.Avion;
import com.airwings.app.model.entity.usuario.EstadoVuelo;

import lombok.Data;

@Entity
@Data
public class Vuelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String codigo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDespegue;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAterrizaje;
	private Long duracion;
	private Double distancia;
	private Double precio;
	
	@OneToMany(mappedBy = "vuelo", fetch = FetchType.EAGER)
	private List<VueloAsiento> vueloAsiento;
	
	
	@OneToOne
	private Aeropuerto origen;
	@OneToOne
	private Aeropuerto destino;	
	@ManyToOne
	private Avion avion;
	@ManyToOne
	private EstadoVuelo estado;
	@ManyToOne
	private Viaje viaje;
	
	public VueloDto getVueloDto() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dh = new SimpleDateFormat("HH:mm");
		
		VueloDto v = new VueloDto();
		v.setId(id);
		v.setCodigo(codigo);
		v.setFechaDespegue(df.format(fechaDespegue));
		v.setHoraDespegue(dh.format(fechaDespegue));
		v.setFechaAterrizaje(df.format(fechaAterrizaje));
		v.setHoraAterrizaje(dh.format(fechaAterrizaje));
		v.setDistancia(distancia);
		v.setPrecio(precio);
		v.setAeropOrigenId(origen.getId());
		v.setAeropDestinoId(destino.getId());
		v.setAvionId(avion.getId());
		v.setViaje(viaje.getId());
		return v;
	}
}
