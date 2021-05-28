package com.airwings.app.services.avion;

import com.airwings.app.model.DAO.avion.TipoAvionDao;
import com.airwings.app.model.entity.avion.TipoAvion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoAvionServiceImpl implements TipoAvionService {

    @Autowired
    private TipoAvionDao tipoAvionDao;

    @Override
    @Transactional(readOnly = true)
    public List<TipoAvion> listaTipoAvion() {
        return (List<TipoAvion>) tipoAvionDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(TipoAvion tipoAvion) {
        tipoAvionDao.save(tipoAvion);
    }

    @Override
    @Transactional
    public void eliminar(TipoAvion tipoAvion) {
        tipoAvionDao.delete(tipoAvion);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoAvion encontrarTipoAvion(TipoAvion tipoAvion) {
        return tipoAvionDao.findById(tipoAvion.getId()).orElse(null);
    }

}
