package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.usuario.EstadoCivil;

@Repository
public interface EstadoCivilDao extends JpaRepository<EstadoCivil, Long> {

}
