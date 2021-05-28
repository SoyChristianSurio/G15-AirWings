
package com.airwings.app.model.DAO.avion;

import com.airwings.app.model.entity.avion.TipoAvion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoAvionDao extends JpaRepository<TipoAvion, Long>{
    
}
