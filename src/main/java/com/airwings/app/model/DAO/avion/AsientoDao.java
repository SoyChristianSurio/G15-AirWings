package com.airwings.app.model.DAO.avion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.avion.Asiento;

@Repository
public interface AsientoDao extends JpaRepository<Asiento, Long> {

}
