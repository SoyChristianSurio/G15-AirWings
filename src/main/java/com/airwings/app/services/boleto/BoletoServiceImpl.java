package com.airwings.app.services.boleto;

import com.airwings.app.model.DAO.avion.ClaseDao;
import com.airwings.app.model.DAO.boleto.BoletoDao;
import com.airwings.app.model.DAO.boleto.VueloAsientoDao;
import com.airwings.app.model.entity.boleto.Boleto;
import com.airwings.app.model.entity.boleto.Viaje;
import com.airwings.app.model.entity.boleto.ViajeVuelo;
import com.airwings.app.model.entity.boleto.VueloAsiento;
import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.avion.ClaseService;
import com.airwings.app.services.usuario.UsuarioService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoletoServiceImpl implements BoletoService {

    @Autowired
    private BoletoDao boletoDao;
    @Autowired
    ViajeService viajeService;
    @Autowired
    UsuarioService usService;
    @Autowired 
    VueloAsientoDao vaDao;
    @Autowired
    ClaseService claseService;

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> listaBoleto() {
        return (List<Boleto>) boletoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Boleto boleto) {
        boletoDao.save(boleto);
    }

    @Override
    @Transactional
    public void eliminar(Boleto boleto) {
        boletoDao.delete(boleto);
    }

    @Override
    @Transactional(readOnly = true)
    public Boleto encontrarBoleto(Boleto boleto) {
        return boletoDao.findById(boleto.getId()).orElse(null);
    }

	@Override
	public Boleto comprarBoleto(Long viajeId, Long usuarioId, Long claseId) {
		Viaje viaje = viajeService.findById(viajeId);
		Usuario usuario = usService.findById(usuarioId);
		Boleto boleto = new Boleto();
		boleto.setPrecio(0.0);
		/*Descontar un asiento disponible*/
		for(ViajeVuelo vv : viaje.getVvuelos()) {
			for(VueloAsiento va: vv.getVuelo().getVueloAsiento()) {
				if(va.getAsiento().getClase().getId()==claseId) {
					boleto.setPrecio(boleto.getPrecio()+va.getAsiento().getPrecioAsiento());
					va.setDisponible(va.getDisponible()-1);
					vaDao.save(va);
				}
			}
		}
		
		boleto.setClase(claseService.findById(claseId));
		boleto.setCliente(usService.findById(usuarioId));
		boleto.setViaje(viajeService.findById(viajeId));
		boleto.setPrecio(boleto.getPrecio()+boleto.getViaje().getPrecio());
		
		return boletoDao.save(boleto);
	}

}
