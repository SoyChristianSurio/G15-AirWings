package com.airwings.app.model.DAO.avion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.avion.Asiento;

public interface AsientoDao extends JpaRepository<Asiento, Long> {

}
