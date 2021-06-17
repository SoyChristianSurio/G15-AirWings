package com.airwings.app.model.DAO.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.usuario.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
	@Query(value = "SELECT * from allRolAeropAdmin()", nativeQuery = true)
	public List<Usuario> allRolAeropAdmin();
	
	@Query(value = "SELECT * from allAdminNotOfAerop(?1)", nativeQuery = true)
	public List<Usuario> allAdminNotOfAerop(Long id);
	@Query(value = "SELECT * from allAdminNotOfAerol(?1)", nativeQuery = true)
	public List<Usuario> allAdminNotOfAerol(Long id);
	
	@Query(value = "SELECT * from allAdminOfAerop(?1)", nativeQuery = true)
	public List<Usuario> allAdminOfAerop(Long id);
	@Query(value = "SELECT * from allAdminOfAerol(?1)", nativeQuery = true)
	public List<Usuario> allAdminOfAerol(Long id);
	
	@Query(value = "SELECT * from findUserAdminOfAerop(?1,?2)", nativeQuery = true)
	public Usuario findUserAdminOfAerop(Long userId, Long aeropId);
	@Query(value = "SELECT * from findUserAdminOfAerol(?1,?2)", nativeQuery = true)
	public Usuario findUserAdminOfAerol(Long userId, Long aeropId);
	
	@Query(value="UPDATE usuario u SET u.contador_bloqueo = ?1 WHERE u.username = ?2", nativeQuery = true)
	@Modifying
	public void updateContadorBloqueo(Integer intentosFallidos, String username);
	
}
