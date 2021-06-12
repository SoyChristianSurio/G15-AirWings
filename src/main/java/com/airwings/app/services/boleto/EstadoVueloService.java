package com.airwings.app.services.boleto;

import com.airwings.app.model.entity.usuario.EstadoVuelo;
import java.util.List;

public interface EstadoVueloService {

    public List<EstadoVuelo> findAll();

    public void guardar(EstadoVuelo estadoVuelo);

    public void eliminar(EstadoVuelo estadoVuelo);

    public EstadoVuelo encontrarEstadoVuelo(EstadoVuelo estadoVuelo);
}
