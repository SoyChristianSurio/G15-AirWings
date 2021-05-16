package com.airwings.app.model.entity.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@JoinTable(
			name = "rol_permiso",
			joinColumns = @JoinColumn(name="fk_rol", nullable = false),
			inverseJoinColumns = @JoinColumn(name="fk_permiso", nullable = false)
		)
		@ManyToMany(fetch = FetchType.LAZY)
	private List<Permiso> permisos;
}
