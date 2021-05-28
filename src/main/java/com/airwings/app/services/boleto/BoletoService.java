
package com.airwings.app.services.boleto;

import com.airwings.app.model.entity.boleto.Boleto;
import java.util.List;


public interface BoletoService {
    public List<Boleto> listaBoleto();
    
    public void guardar(Boleto boleto);
    
    public void eliminar(Boleto boleto);
    
    public Boleto encontrarBoleto(Boleto boleto);
}
