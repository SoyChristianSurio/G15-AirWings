package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.usuario.TipoDocumento;

@Repository
public interface TipoDocumentoDao extends JpaRepository<TipoDocumento, Long> {

}
