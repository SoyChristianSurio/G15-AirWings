package com.airwings.app.controllers.gestionAeropuertos;

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

import com.airwings.app.model.entity.Ciudad;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.CiudadService;
import com.airwings.app.services.PaisService;

@Controller
@RequestMapping("/gestion/aeropuerto")
public class AeropuertoController {

	@Autowired
	AeropuertoService aeropuertoService;	
	
	@Secured("ROLE_admin")
	@GetMapping("/lista")
	public String lista(@PathVariable(name = "id")Long id, Model model ) {
		model.addAttribute("pais", paisService.findById(id));
		model.addAttribute("newCiudad", new Ciudad());
		return "/parametrizables/ciudad/lista";
	}
	
	@GetMapping("/editar/{idc}")
	public String guardar(@PathVariable(name = "id")Long id, @PathVariable(name = "idc")Long idc, Model model) {
		model.addAttribute("myCiudad", ciudadService.findById(idc));
		model.addAttribute("pais", paisService.findById(id));
		model.addAttribute("newCiudad", new Ciudad());
		return "/parametrizables/ciudad/lista";
	}
	
	@GetMapping("/eliminar/{idc}")
	public String eliminar(@PathVariable(name = "id")Long id, @PathVariable(name = "idc")Long idc, Model model) {
		model.addAttribute("delCiudad", ciudadService.findById(idc));
		model.addAttribute("pais", paisService.findById(id));
		model.addAttribute("newCiudad", new Ciudad());
		return "/parametrizables/ciudad/lista";
	}
	
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("newCiudad")Ciudad ciudad, BindingResult result, @PathVariable(name = "id")Long id, Model model, RedirectAttributes flash) {
		System.out.println("Crear nombre de pais:"+paisService.findById(id).getNombre());
		if(result.hasErrors()) {
			model.addAttribute("pais", paisService.findById(id));
			model.addAttribute("errorCrear","");
			return "/parametrizables/ciudad/lista";
		}
		ciudad.setPais(paisService.findById(id));
		ciudadService.save(ciudad);
		flash.addFlashAttribute("success","TRREMENEEEDO Guardado con éxito");
		return "redirect:/gestion/ciudad/p/"+id+"/lista";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid @ModelAttribute("myCiudad")Ciudad ciudad, BindingResult result, @PathVariable(name = "id")Long id, Model model, RedirectAttributes flash) {
		System.out.println("Editar nombre de pais:"+paisService.findById(id).getNombre());
		if(result.hasErrors()) {
			model.addAttribute("pais", paisService.findById(id));
			model.addAttribute("newCiudad", new Ciudad());
			return "/parametrizables/ciudad/lista";
		}
		ciudad.setPais(paisService.findById(id));
		ciudadService.save(ciudad);
		flash.addFlashAttribute("info","TRREMENEEEDO Editado con éxito");
		return "redirect:/gestion/ciudad/p/"+id+"/lista";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute("myPais")Ciudad ciudad, BindingResult result, @PathVariable(name = "id")Long id, Model model, RedirectAttributes flash) {
		System.out.println("Editar nombre de pais:"+paisService.findById(id).getNombre());		
		model.addAttribute("pais", paisService.findById(id));
		model.addAttribute("newCiudad", new Ciudad());
		model.addAttribute("delCiudad","");
		
		ciudadService.deleteById(ciudad.getId());
		flash.addFlashAttribute("warning","TRREMENEEEDO Eliminado con éxito");
		return "redirect:/gestion/ciudad/p/"+id+"/lista";
	}
}
