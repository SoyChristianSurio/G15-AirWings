
package com.airwings.app.model.DAO.aerolinea;

import com.airwings.app.model.entity.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AerolineaDao extends JpaRepository<Aerolinea, Long>{
    
}
