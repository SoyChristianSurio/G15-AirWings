package com.airwings.app.controllers.gestionParametrizable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airwings.app.model.entity.Pais;
import com.airwings.app.services.PaisService;

@Controller
@RequestMapping("/gestion/pais")
public class PaisController {

	@Autowired
	PaisService paisService;
	
	@Secured("ROLE_admin")
	@GetMapping("/lista")
	public String lista(Model model ) {
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("newPais", new Pais());
		return "/parametrizables/pais/lista";
	}
	
	@PostMapping
	public String guardar(@Valid @ModelAttribute("newPais")Pais pais, BindingResult result) {
		
		return "redirect:/gestion/pais/lista";
	}
	
}
