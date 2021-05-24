package com.airwings.app.model.DAO.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.usuario.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
