package com.airwings.app.model.DAO.boleto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.boleto.VueloAsiento;

public interface VueloAsientoDao extends JpaRepository<VueloAsiento, Long> {
	
}
