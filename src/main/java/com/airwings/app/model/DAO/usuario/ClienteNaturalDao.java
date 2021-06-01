package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.usuario.ClienteNatural;

public interface ClienteNaturalDao extends JpaRepository<ClienteNatural, Long>{
	
}
