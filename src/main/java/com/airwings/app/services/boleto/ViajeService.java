package com.airwings.app.services.boleto;

import com.airwings.app.model.entity.boleto.Viaje;
import java.util.List;

public interface ViajeService {

    public List<Viaje> listaViaje();

    public void guardar(Viaje viaje);

    public void eliminar(Viaje viaje);

    public Viaje encontrarViaje(Viaje viaje);
}
