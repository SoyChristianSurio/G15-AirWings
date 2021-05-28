package com.airwings.app.services.avion;

import com.airwings.app.model.entity.avion.TipoAvion;
import java.util.List;

public interface TipoAvionService {

    public List<TipoAvion> listaTipoAvion();

    public void guardar(TipoAvion tipoAvion);

    public void eliminar(TipoAvion tipoAvion);

    public TipoAvion encontrarTipoAvion(TipoAvion tipoAvion);
}
