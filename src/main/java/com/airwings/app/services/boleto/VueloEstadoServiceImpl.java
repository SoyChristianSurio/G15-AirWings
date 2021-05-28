package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.VueloEstadoDao;
import com.airwings.app.model.entity.boleto.VueloEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VueloEstadoServiceImpl implements VueloEstadoService {

    @Autowired
    private VueloEstadoDao vueloEstadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<VueloEstado> listaVueloEstado() {
        return (List<VueloEstado>) vueloEstadoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(VueloEstado vueloEstado) {
        vueloEstadoDao.save(vueloEstado);
    }

    @Override
    @Transactional
    public void eliminar(VueloEstado vueloEstado) {
        vueloEstadoDao.delete(vueloEstado);
    }

    @Override
    @Transactional(readOnly = true)
    public VueloEstado encontrarVueloEstado(VueloEstado vueloEstado) {
        return vueloEstadoDao.findById(vueloEstado.getId()).orElse(null);
    }

}
