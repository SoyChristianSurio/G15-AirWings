
package com.airwings.app.model.DAO.avion;

import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.avion.Avion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AvionDao extends JpaRepository<Avion, Long> {
    
	public List<Avion> findAllByAerolinea(Aerolinea a);
}
