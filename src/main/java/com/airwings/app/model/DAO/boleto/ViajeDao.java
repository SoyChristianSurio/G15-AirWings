
package com.airwings.app.model.DAO.boleto;

import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.boleto.Viaje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeDao extends JpaRepository<Viaje, Long>{
    public List<Viaje> findAllByAerolinea(Aerolinea a);
}
