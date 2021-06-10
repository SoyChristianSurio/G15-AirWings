package com.airwings.app.services.boleto;

import com.airwings.app.model.DTO.vuelo.VueloDto;
import com.airwings.app.model.entity.boleto.Vuelo;

import java.text.ParseException;
import java.util.List;

public interface VueloService {

    public List<Vuelo> listaVuelo();

    public void guardar(Vuelo vuelo);
    
    public Vuelo save(VueloDto v) throws ParseException;

    public void eliminar(Vuelo vuelo);

    public Vuelo encontrarVuelo(Vuelo vuelo);
}
