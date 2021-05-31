package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.usuario.ClienteEmpresa;

public interface ClienteEmpresaDao extends JpaRepository<ClienteEmpresa, Long> {

}
