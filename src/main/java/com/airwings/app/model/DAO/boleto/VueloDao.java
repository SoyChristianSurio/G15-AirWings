
package com.airwings.app.model.DAO.boleto;

import com.airwings.app.model.entity.boleto.Vuelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VueloDao extends JpaRepository<Vuelo, Long>{
    
	@Query(value = "select v.* from vuelo v inner join avion a on (v.avion_id=a.id) where a.aerolinea_id=?1",nativeQuery = true)
	public List<Vuelo> findAllByAerolinea(Long id);
}
