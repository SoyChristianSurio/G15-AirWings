
package com.airwings.app.model.DAO.boleto;

import com.airwings.app.model.entity.boleto.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloDao extends JpaRepository<Vuelo, Long>{
    
}
