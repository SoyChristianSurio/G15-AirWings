package com.airwings.app.controllers.boleto;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airwings.app.model.DAO.boleto.ViajeVueloDao;
import com.airwings.app.model.DTO.vuelo.VueloDto;
import com.airwings.app.services.AerolineaService;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.avion.AvionService;
import com.airwings.app.services.boleto.ViajeService;
import com.airwings.app.services.boleto.VueloService;

@Controller
@RequestMapping("gestion/viaje")
public class ViajeController {
	
	@Autowired
	AerolineaService aerolService;
	@Autowired
	AeropuertoService aeropService;
	@Autowired
	AvionService avionService;
	@Autowired
	VueloService vueloService;
	@Autowired
	ViajeService viajeService;
	@Autowired
	ViajeVueloDao viajeVueloDao;
	
	
	@GetMapping("/{id}/vuelo")
	public String listar(@PathVariable(name = "id")Long idVj, Model model) {
		if(viajeService.findById(idVj)==null) return "redirect:/";
		model.addAttribute("newVuelo", new VueloDto());
		model.addAttribute("myViaje",viajeService.findById(idVj));
		model.addAttribute("vuelos", viajeVueloDao.findAllByViajeOrderByCorrelAsc( viajeService.findById(idVj) ) );
		model.addAttribute("aerops",aeropService.findAll());
		model.addAttribute("aviones", avionService.findAllByAerolinea(viajeService.findById(idVj).getAerolinea()) );
		return "aerolinea/gestion_viaje";
	}
	
	@PostMapping("/{id}/guardar")
	public String guardar(@Valid @ModelAttribute("newVuelo")VueloDto vuelo, BindingResult result, @PathVariable(name="id")Long idVj, Model model) throws ParseException {
		vuelo.setId(null);
		vuelo.setViaje(idVj);
		if(result.hasErrors()) {
			model.addAttribute("myViaje",viajeService.findById(idVj));
			model.addAttribute("aerops",aeropService.findAll());
			model.addAttribute("vuelos", viajeVueloDao.findAllByViajeOrderByCorrelAsc( viajeService.findById(idVj) ) );
			model.addAttribute("aviones", avionService.findAllByAerolinea(viajeService.findById(idVj).getAerolinea()) );
			model.addAttribute("errorCrear","");
			return "aerolinea/gestion_viaje";
		}
		vueloService.save(vuelo);
		viajeService.actualizarViaje(viajeService.findById(idVj));
		
		return "redirect:/gestion/viaje/"+idVj+"/vuelo";
	}
}
