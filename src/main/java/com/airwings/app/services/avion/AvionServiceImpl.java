package com.airwings.app.services.avion;

import com.airwings.app.model.DAO.avion.AvionDao;
import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.avion.Avion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvionServiceImpl implements AvionService {

    @Autowired
    private AvionDao avionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Avion> listaAvion() {
        return (List<Avion>) avionDao.findAll();
    }

    @Override
    @Transactional
    public Avion guardar(Avion avion) {
        return avionDao.save(avion);
    }

    @Override
    @Transactional
    public void eliminar(Avion avion) {
        avionDao.delete(avion);
    }

    @Override
    @Transactional(readOnly = true)
    public Avion encontrarAvion(Avion avion) {
        return avionDao.findById(avion.getId()).orElse(null);
    }

	@Override
	public List<Avion> findAllByAerolinea(Aerolinea a) {
		return avionDao.findAllByAerolinea(a);
	}

	@Override
	public Avion findById(Long id) {
		return avionDao.findById(id).orElse(null);
	}

}
