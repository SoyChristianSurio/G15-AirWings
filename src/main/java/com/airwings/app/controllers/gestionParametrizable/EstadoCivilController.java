package com.airwings.app.controllers.gestionParametrizable;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airwings.app.model.DAO.usuario.EstadoCivilDao;
import com.airwings.app.model.entity.usuario.EstadoCivil;

@Controller
@RequestMapping("/gestion/estCivil")
public class EstadoCivilController {

	@Autowired
	EstadoCivilDao ecDao;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		
		model.addAttribute("newObj", new EstadoCivil());
		model.addAttribute("title", "estCivil");
		model.addAttribute("lista", ecDao.findAll());
		model.addAttribute("mensaje", "estado civil");
		return "/parametrizables/lista_estCivil";
	}
	
	@GetMapping("/editar/{id}")
	public String editarG(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("myObj", ecDao.findById(id).orElse(null));
		model.addAttribute("newObj", new EstadoCivil());
		model.addAttribute("title", "estCivil");
		model.addAttribute("lista", ecDao.findAll());	
		return "/parametrizables/lista_estCivil";
	}
	
	@GetMapping("/eliminar/{id}")
	public String elminiar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("delObj", ecDao.findById(id).orElse(null));
		model.addAttribute("newObj", new EstadoCivil());
		model.addAttribute("title", "estCivil");
		model.addAttribute("lista", ecDao.findAll());	
		return "/parametrizables/lista_estCivil";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("newObj")EstadoCivil obj, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("title", "estCivil");
			model.addAttribute("lista", ecDao.findAll());
			model.addAttribute("errorCrear","");
			return "/parametrizables/lista_estCivil";
		}
		ecDao.save(obj);
		flash.addFlashAttribute("success","Guardado con éxito");
		return "redirect:/gestion/estCivil/lista";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid @ModelAttribute("myObj")EstadoCivil obj, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("title", "estCivil");
			model.addAttribute("lista", ecDao.findAll());
			return "/parametrizables/lista_estCivil";
		}
		ecDao.save(obj);
		flash.addFlashAttribute("success","Editado con éxito");
		return "redirect:/gestion/estCivil/lista";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute("delObj")EstadoCivil obj, BindingResult result, Model model, RedirectAttributes flash) {

		ecDao.deleteById(obj.getId());
		flash.addFlashAttribute("warning","Eliminado con éxito");
		return "redirect:/gestion/estCivil/lista";
	}
	
}
