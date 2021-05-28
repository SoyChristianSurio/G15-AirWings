
package com.airwings.app.model.DAO.avion;

import com.airwings.app.model.entity.avion.Avion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AvionDao extends JpaRepository<Avion, Long> {
    
}
