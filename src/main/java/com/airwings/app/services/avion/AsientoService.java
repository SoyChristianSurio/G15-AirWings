
package com.airwings.app.services.avion;

import com.airwings.app.model.entity.avion.Asiento;
import java.util.List;


public interface AsientoService {
    public List<Asiento> listaAsiento();
    
    public void guardar(Asiento asiento);
    
    public void eliminar(Asiento asiento);
    
    public Asiento encontrarAsiento(Asiento asiento);
}
