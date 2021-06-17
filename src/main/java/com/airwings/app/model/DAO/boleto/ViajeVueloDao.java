package com.airwings.app.model.DAO.boleto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airwings.app.model.entity.boleto.Viaje;
import com.airwings.app.model.entity.boleto.ViajeVuelo;

@Repository
public interface ViajeVueloDao extends JpaRepository<ViajeVuelo, Long>{
	
	public List<ViajeVuelo> findAllByViajeOrderByCorrelAsc(Viaje viaje);
	
}
