package com.airwings.app.model.DAO.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airwings.app.model.entity.Aeropuerto;
import com.airwings.app.model.entity.usuario.AdminAerolinea;

@Repository
public interface AdminAerolineaDao extends JpaRepository<AdminAerolinea, Long> {
	@Query(value="Select id from admin_aerolinea where usuario_id=?1 and aerolinea_id=?2", nativeQuery = true)
	@Transactional(readOnly = true)
	public Long getIdRegistroAdminAerol(Long userId, Long aeropId);
	
	public List<AdminAerolinea> findAllByAerolinea(Aeropuerto aerop);
}
