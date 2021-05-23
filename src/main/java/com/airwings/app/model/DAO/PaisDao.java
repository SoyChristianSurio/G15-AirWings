package com.airwings.app.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.Pais;

public interface PaisDao extends JpaRepository<Pais, Long> {

}
