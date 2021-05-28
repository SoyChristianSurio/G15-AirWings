package com.airwings.app.services.boleto;

import com.airwings.app.model.entity.boleto.Vuelo;
import java.util.List;

public interface VueloService {

    public List<Vuelo> listaVuelo();

    public void guardar(Vuelo vuelo);

    public void eliminar(Vuelo vuelo);

    public Vuelo encontrarVuelo(Vuelo vuelo);
}
