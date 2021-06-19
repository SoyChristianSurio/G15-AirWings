package com.airwings.app.services.avion;

import com.airwings.app.model.DAO.avion.ClaseDao;
import com.airwings.app.model.entity.avion.Clase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseDao claseDao;

    @Override
    @Transactional(readOnly = true)
    public List<Clase> listaClase() {
        return (List<Clase>) claseDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Clase clase) {
        claseDao.save(clase);
    }

    @Override
    @Transactional
    public void eliminar(Clase clase) {
        claseDao.delete(clase);
    }

    @Override
    @Transactional(readOnly = true)
    public Clase encontrarClase(Clase clase) {
        return claseDao.findById(clase.getId()).orElse(null);
    }

	@Override
	public Clase findById(Long id) {
		return claseDao.findById(id).orElse(null);
	}

}
