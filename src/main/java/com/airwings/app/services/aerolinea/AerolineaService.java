
package com.airwings.app.services.aerolinea;

import com.airwings.app.model.entity.Aerolinea;
import java.util.List;


public interface AerolineaService {
    public List<Aerolinea> listaAerolinea();
    
    public void guardar(Aerolinea aerolinea);
    
    public void eliminar(Aerolinea aerolinea);
    
    public Aerolinea encontrarAerolinea(Aerolinea aerolinea);
}
