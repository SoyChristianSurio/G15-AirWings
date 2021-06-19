package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.boleto.ViajeVueloDao;
import com.airwings.app.model.DAO.boleto.VueloAsientoDao;
import com.airwings.app.model.DAO.boleto.VueloDao;
import com.airwings.app.model.DTO.vuelo.VueloDto;
import com.airwings.app.model.entity.avion.Asiento;
import com.airwings.app.model.entity.boleto.ViajeVuelo;
import com.airwings.app.model.entity.boleto.Vuelo;
import com.airwings.app.model.entity.boleto.VueloAsiento;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.avion.AvionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloDao vueloDao;
    @Autowired
    AeropuertoService aeropService;
    @Autowired
    AvionService avionService;
    @Autowired
    ViajeService viajeService;
    @Autowired
    ViajeVueloDao viajeVueloDao;
    @Autowired
    VueloAsientoDao vueloAsientoDao;

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

	@Override
	public Vuelo save(VueloDto v) throws ParseException {
		/* Convertir las fechas de String a Date*/
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		Date dd = df.parse(""+v.getFechaDespegue()+" "+v.getHoraDespegue());
		Date da = df.parse(""+v.getFechaAterrizaje()+" "+v.getHoraAterrizaje());
		long diffInMillies = da.getTime() - dd.getTime();
		Long minutos = TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
		boolean nuevo=true;
		/*Convertir Vuelo de DTO a ENTITY*/
		Vuelo vu;
		if(v.getId()==null) {vu = new Vuelo();} 
		else {
			vu = vueloDao.findById(v.getId()).orElse(null);
			vu.setId(v.getId());
			nuevo=false;
		}
		vu.setCodigo(avionService.findById(v.getAvionId()).getAerolinea().getCodigo()+"-".concat(v.getCodigo()));
		vu.setFechaDespegue(dd);
		vu.setFechaAterrizaje(da);
		vu.setDuracion(minutos);
		vu.setDistancia(v.getDistancia());
		vu.setPrecio(v.getPrecio());
		vu.setOrigen(aeropService.findById(v.getAeropOrigenId()));	
		vu.setDestino(aeropService.findById(v.getAeropDestinoId()));
		vu.setAvion(avionService.findById(v.getAvionId()));		
		if(v.getViaje()!=null) vu.setViaje(viajeService.findById(v.getViaje()));
		
		System.out.println("id: "+vu.getId());
		System.out.println("viajeId: "+vu.getViaje().getId());
		vu=vueloDao.save(vu);
		
		
		/*Crear los registros en VUELO - ASIENTO*/		
		if(nuevo) {
			for(Asiento asiento:vu.getAvion().getAsientos()) {
				VueloAsiento va = new VueloAsiento();
				va.setAsiento(asiento);
				va.setVuelo(vu);
				va.setDisponible(asiento.getCantidadAsiento());
				va.setId(null);
				vueloAsientoDao.save(va);
			}
		}
		if(nuevo) {
			/*Crear el registro de VUELO - VIAJE*/
			ViajeVuelo vv = new ViajeVuelo();
			vv.setViaje( viajeService.findById(v.getViaje()) );
			vv.setVuelo(vu);
			vv.setCorrel( viajeVueloDao.findAllByViajeOrderByCorrelAsc(vv.getViaje()).size() + 1 );
			viajeVueloDao.save(vv);
		}
		return vu;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vuelo> findAllByAerolinea(Long id) {
		return vueloDao.findAllByAerolinea(id);
	}

	@Override
	public Vuelo findById(Long id) {
		return vueloDao.findById(id).orElse(null);
	}

}
