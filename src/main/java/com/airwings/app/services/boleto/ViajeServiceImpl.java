package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.ViajeDao;
import com.airwings.app.model.DAO.boleto.ViajeVueloDao;
import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.boleto.Viaje;
import com.airwings.app.model.entity.boleto.ViajeVuelo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    ViajeDao viajeDao;
    @Autowired
    ViajeVueloDao viajeVueloDao;

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

	@Override
	public List<Viaje> findAllByAerolinea(Aerolinea a) {
		return viajeDao.findAllByAerolinea(a);
	}

	@Override
	public Viaje findById(Long id) {
		return viajeDao.findById(id).orElse(null);
	}

	@Override
	public Viaje actualizarViaje(Viaje viaje) {
		List<ViajeVuelo> vuelos = viajeVueloDao.findAllByViajeOrderByCorrelAsc(viaje);
		viaje.setEscalas(vuelos.size());
		/*Establecer vuelo origen y vuelo destino*/
		Double precio = 0.0;
		for(ViajeVuelo vv : vuelos) {
			precio+=vv.getVuelo().getPrecio();
			if(vv.getCorrel()==1) viaje.setVueloOrigen(vv.getVuelo());
			if(vv.getCorrel()==vuelos.size()) viaje.setVueloDestino(vv.getVuelo());
		}
		viaje.setPrecio(precio);
		/* Nueva duración de viaje*/
		long diffInMillies = viaje.getVueloDestino().getFechaAterrizaje().getTime() - viaje.getVueloOrigen().getFechaDespegue().getTime();
		viaje.setDuracion(TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS));
		
		
		return viajeDao.save(viaje);
	}
	
	

}
