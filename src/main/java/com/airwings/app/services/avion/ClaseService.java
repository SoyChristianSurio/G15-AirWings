
package com.airwings.app.services.avion;

import com.airwings.app.model.entity.avion.Clase;
import java.util.List;


public interface ClaseService {
    public List<Clase> listaClase();
    
    public void guardar(Clase clase);
    
    public void eliminar(Clase clase);
    
    public Clase encontrarClase(Clase clase);
    
    public Clase findById(Long id);
}
