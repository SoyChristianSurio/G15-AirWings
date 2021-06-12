package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.usuario.EstadoVueloDao;
import com.airwings.app.model.entity.usuario.EstadoVuelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoVueloServiceImpl implements EstadoVueloService {

    @Autowired
    EstadoVueloDao EstadoVueloDao;

    @Override
    @Transactional(readOnly = true)
    public List<EstadoVuelo> findAll() {
        return (List<EstadoVuelo>) EstadoVueloDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(EstadoVuelo EstadoVuelo) {
        EstadoVueloDao.save(EstadoVuelo);
    }

    @Override
    @Transactional
    public void eliminar(EstadoVuelo EstadoVuelo) {
        EstadoVueloDao.delete(EstadoVuelo);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoVuelo encontrarEstadoVuelo(EstadoVuelo EstadoVuelo) {
        return EstadoVueloDao.findById(EstadoVuelo.getId()).orElse(null);
    }

}
