package com.airwings.app.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.Ciudad;

public interface CiudadDao extends JpaRepository<Ciudad, Long> {

}
