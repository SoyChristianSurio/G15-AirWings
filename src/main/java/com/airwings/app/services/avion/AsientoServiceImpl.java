package com.airwings.app.services.avion;

import com.airwings.app.model.DAO.avion.AsientoDao;
import com.airwings.app.model.entity.avion.Asiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsientoServiceImpl implements AsientoService {

    @Autowired
    private AsientoDao asientoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Asiento> listaAsiento() {
        return (List<Asiento>) asientoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Asiento asiento) {
        asientoDao.save(asiento);
    }

    @Override
    @Transactional
    public void eliminar(Asiento asiento) {
        asientoDao.delete(asiento);
    }

    @Override
    @Transactional(readOnly = true)
    public Asiento encontrarAsiento(Asiento asiento) {
        return asientoDao.findById(asiento.getId()).orElse(null);
    }

}
