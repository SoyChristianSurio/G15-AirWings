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

import com.airwings.app.model.DTO.vuelo.VueloDto;
import com.airwings.app.services.AerolineaService;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.avion.AvionService;
import com.airwings.app.services.boleto.VueloService;

@Controller
@RequestMapping("gestion/vuelo")
public class VueloController {
	
	@Autowired
	AerolineaService aerolService;
	@Autowired
	AeropuertoService aeropService;
	@Autowired
	AvionService avionService;
	@Autowired
	VueloService vueloService;
	
	
	@GetMapping("/al/{idAl}/lista")
	public String listar(@PathVariable(name = "idAl")Long idAl, Model model) {
		model.addAttribute("newVuelo", new VueloDto());
		model.addAttribute("aerolinea", aerolService.findById(idAl));
		model.addAttribute("vuelos", vueloService.findAllByAerolinea(idAl));
		
		return "vuelo/vuelo_lista";
	}
	
	@PostMapping("/al/{id}/guardar")
	public String guardar(@Valid @ModelAttribute("newVuelo")VueloDto vuelo, BindingResult result, @PathVariable(name="id")Long idAl, Model model) throws ParseException {
		vuelo.setId(null);
		if(result.hasErrors()) {
			model.addAttribute("aerolinea", aerolService.findById(idAl));
			model.addAttribute("aerops",aeropService.findAll());
			model.addAttribute("aviones", avionService.findAllByAerolinea(aerolService.findById(idAl)) );
			model.addAttribute("errorCrear","");
			return "vuelo/vuelo_lista";
		}
		vueloService.save(vuelo);
		
		return "redirect:/gestion/vuelo/al/"+idAl+"/lista";
	}
}
