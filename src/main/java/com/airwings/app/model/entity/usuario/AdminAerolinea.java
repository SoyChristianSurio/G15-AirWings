package com.airwings.app.model.entity.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airwings.app.model.entity.Aerolinea;

import lombok.Data;

@Entity
@Data
public class AdminAerolinea  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Aerolinea aerolinea;
	
	@ManyToOne
	private Usuario usuario;
}
