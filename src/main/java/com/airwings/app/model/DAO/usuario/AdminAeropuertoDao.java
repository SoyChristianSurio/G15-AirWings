package com.airwings.app.model.DAO.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;

@Repository
public interface AdminAeropuertoDao extends JpaRepository<AdminAeropuerto, Long> {
		
	@Query(value="Select id from admin_aeropuerto where usuario_id=?1 and aeropuerto_id=?2", nativeQuery = true)
	@Transactional(readOnly = true)
	public Long getIdRegistroAdminAerop(Long userId, Long aeropId);
	
	public List<AdminAeropuerto> findAllByAeropuerto(Aeropuerto aerop);
}
