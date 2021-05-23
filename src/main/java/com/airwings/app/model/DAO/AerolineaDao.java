package com.airwings.app.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.Aerolinea;

@Repository
public interface AerolineaDao extends JpaRepository<Aerolinea, Long> {

}
