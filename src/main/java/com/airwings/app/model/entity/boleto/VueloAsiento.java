package com.airwings.app.model.entity.boleto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airwings.app.model.entity.avion.Asiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloAsiento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Vuelo vuelo;
	@ManyToOne(fetch = FetchType.EAGER)
	private Asiento asiento;
	
	private Integer disponible;

	
}
