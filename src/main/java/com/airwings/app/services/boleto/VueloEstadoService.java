package com.airwings.app.services.boleto;

import com.airwings.app.model.entity.boleto.VueloEstado;
import java.util.List;

public interface VueloEstadoService {

    public List<VueloEstado> listaVueloEstado();

    public void guardar(VueloEstado vueloEstado);

    public void eliminar(VueloEstado vueloEstado);

    public VueloEstado encontrarVueloEstado(VueloEstado vueloEstado);
}
