
package com.airwings.app.model.DAO.boleto;

import com.airwings.app.model.entity.boleto.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ViajeDao extends JpaRepository<Viaje, Long>{
    
}
