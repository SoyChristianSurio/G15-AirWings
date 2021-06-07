package com.airwings.app.model.entity.avion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.airwings.app.model.entity.Aerolinea;

import lombok.Data;

@Entity
@Data
@Table(name = "avion")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String codigo;
	private String modelo;
	private String marca;
	
	@ManyToOne
	private TipoAvion tipo;		
	@OneToMany(mappedBy = "avion", fetch = FetchType.LAZY)
	private List<Asiento> asientos;	
	@ManyToOne
	private Aerolinea aerolinea;
}
