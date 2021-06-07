
package com.airwings.app.services.aerolinea;

import com.airwings.app.model.DAO.aerolinea.AerolineaDao;
import com.airwings.app.model.entity.Aerolinea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AerolineaServiceImpl implements AerolineaService{
    
    @Autowired
    private AerolineaDao aerolineaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Aerolinea> listaAerolinea() {
        return (List<Aerolinea>) aerolineaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Aerolinea aerolinea) {
        aerolineaDao.save(aerolinea);
    }

    @Override
    @Transactional
    public void eliminar(Aerolinea aerolinea) {
        aerolineaDao.delete(aerolinea);
    }

    @Override
    @Transactional(readOnly = true)
    public Aerolinea encontrarAerolinea(Aerolinea aerolinea) {
        return aerolineaDao.findById(aerolinea.getId()).orElse(null);
    }
    
}
