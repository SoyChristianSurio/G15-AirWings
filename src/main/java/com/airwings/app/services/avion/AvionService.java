
package com.airwings.app.services.avion;

import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.avion.Avion;
import java.util.List;


public interface AvionService {
    public List<Avion> listaAvion();
    
    public void guardar(Avion avion);
    
    public void eliminar(Avion avion);
    
    public Avion encontrarAvion(Avion avion);
    
    public Avion findById(Long id);
    
    public List<Avion> findAllByAerolinea(Aerolinea a);
}
