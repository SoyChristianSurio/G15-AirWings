package com.airwings.app.controllers.gestionParametrizable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/editar/{id}")
	public String guardar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("myPais", paisService.findById(id));
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("newPais", new Pais());
		return "/parametrizables/pais/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("delPais", paisService.findById(id));
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("newPais", new Pais());
		return "/parametrizables/pais/lista";
	}
	
	
	@PostMapping("/guardar")
	public String editar(@Valid @ModelAttribute("newPais")Pais pais, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Crear id de pais:"+pais.getId());
		if(result.hasErrors()) {
			model.addAttribute("paises", paisService.findAll());
			model.addAttribute("errorCrear","");
			return "/parametrizables/pais/lista";
		}
		paisService.save(pais);
		flash.addFlashAttribute("success","TRREMENEEEDO Guardado con éxito");
		return "redirect:/gestion/pais/lista";
	}
	
	@PostMapping("/editar")
	public String guardar(@Valid @ModelAttribute("myPais")Pais pais, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Editar id de pais:"+pais.getId());
		if(result.hasErrors()) {
			model.addAttribute("paises", paisService.findAll());
			model.addAttribute("newPais", new Pais());
			return "/parametrizables/pais/lista";
		}
		paisService.save(pais);
		flash.addFlashAttribute("info","TRREMENEEEDO Editado con éxito");
		return "redirect:/gestion/pais/lista";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute("myPais")Pais pais, Model model, RedirectAttributes flash) {
		System.out.println("id de pais:"+pais.getId());
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("newPais", new Pais());
		model.addAttribute("errorEditar","");
		paisService.deleteById(pais.getId());
		flash.addFlashAttribute("warning","TRREMENEEEDO Eliminado con éxito");
		return "redirect:/gestion/pais/lista";
	}
}
