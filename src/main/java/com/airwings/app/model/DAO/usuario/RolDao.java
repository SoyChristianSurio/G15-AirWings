package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.usuario.Rol;

@Repository
public interface RolDao extends JpaRepository<Rol, Long> {

	public Rol findByNombre(String nombre);
}
