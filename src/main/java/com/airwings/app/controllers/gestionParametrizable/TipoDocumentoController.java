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

import com.airwings.app.model.DAO.usuario.TipoDocumentoDao;
import com.airwings.app.model.entity.usuario.TipoDocumento;

@Controller
@RequestMapping("/gestion/tipoDoc")
public class TipoDocumentoController {

	@Autowired
	TipoDocumentoDao docDao;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		
		model.addAttribute("newObj", new TipoDocumento());
		model.addAttribute("title", "tipoDoc");
		model.addAttribute("lista", docDao.findAll());		
		return "/parametrizables/lista_documento";
	}
	
	@GetMapping("/editar/{id}")
	public String editarG(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("myObj", docDao.findById(id).orElse(null));
		model.addAttribute("newObj", new TipoDocumento());
		model.addAttribute("title", "tipoDoc");
		model.addAttribute("lista", docDao.findAll());	
		return "/parametrizables/lista_documento";
	}
	
	@GetMapping("/eliminar/{id}")
	public String elminiar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("delObj", docDao.findById(id).orElse(null));
		model.addAttribute("newObj", new TipoDocumento());
		model.addAttribute("title", "tipoDoc");
		model.addAttribute("lista", docDao.findAll());	
		return "/parametrizables/lista_documento";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("newObj")TipoDocumento obj, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("title", "tipoDoc");
			model.addAttribute("lista", docDao.findAll());
			model.addAttribute("errorCrear","");
			return "/parametrizables/lista_documento";
		}
		docDao.save(obj);
		flash.addFlashAttribute("success","Guardado con éxito");
		return "redirect:/gestion/tipoDoc/lista";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid @ModelAttribute("myObj")TipoDocumento obj, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("title", "tipoDoc");
			model.addAttribute("lista", docDao.findAll());
			return "/parametrizables/lista_documento";
		}
		docDao.save(obj);
		flash.addFlashAttribute("success","Editado con éxito");
		return "redirect:/gestion/tipoDoc/lista";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute("delObj")TipoDocumento obj, BindingResult result, Model model, RedirectAttributes flash) {

		docDao.deleteById(obj.getId());
		flash.addFlashAttribute("warning","Eliminado con éxito");
		return "redirect:/gestion/tipoDoc/lista";
	}
	
}
