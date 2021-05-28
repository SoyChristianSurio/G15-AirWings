package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.ViajeDao;
import com.airwings.app.model.entity.boleto.Viaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeDao viajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Viaje> listaViaje() {
        return (List<Viaje>) viajeDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Viaje viaje) {
        viajeDao.save(viaje);
    }

    @Override
    @Transactional
    public void eliminar(Viaje viaje) {
        viajeDao.delete(viaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Viaje encontrarViaje(Viaje viaje) {
        return viajeDao.findById(viaje.getId()).orElse(null);
    }

}
