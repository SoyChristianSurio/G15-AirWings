package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.VueloDao;
import com.airwings.app.model.entity.boleto.Vuelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloDao vueloDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vuelo> listaVuelo() {
        return (List<Vuelo>) vueloDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Vuelo vuelo) {
        vueloDao.save(vuelo);
    }

    @Override
    @Transactional
    public void eliminar(Vuelo vuelo) {
        vueloDao.delete(vuelo);
    }

    @Override
    @Transactional(readOnly = true)
    public Vuelo encontrarVuelo(Vuelo vuelo) {
        return vueloDao.findById(vuelo.getId()).orElse(null);
    }

}
