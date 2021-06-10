package com.airwings.app.controllers.gestionUsuarios;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.usuario.RolService;
import com.airwings.app.services.usuario.UsuarioService;

@Controller
@RequestMapping("/gestion/usuario")
public class GestionUsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RolService rolService;
	
	@Secured("ROLE_admin")
	@GetMapping({"/listar","/"})
	public String listarTodos(Model model, Principal principal, Authentication auth) {
		model.addAttribute("title","lista de usuarios");
		model.addAttribute("users", usuarioService.findAll());
		model.addAttribute("fechaSistema",new SimpleDateFormat("dd/MMMM/yyyy").format(new Date()));
		return "usuario/lista";
	}
	
	@GetMapping({"/lock/{id}"})
	public String lock(@PathVariable(name = "id")Long id, Model model, Principal principal, Authentication auth, RedirectAttributes flash) {
		Usuario u = usuarioService.findById(id);
		u.setBloqueado(true);
		usuarioService.save(u);
		return "redirect:/gestion/usuario/listar";
	}
	@GetMapping({"/unlock/{id}"})
	public String unlock(@PathVariable(name = "id")Long id, Model model, Principal principal, Authentication auth, RedirectAttributes flash) {
		Usuario u = usuarioService.findById(id);
		u.setBloqueado(false);
		usuarioService.save(u);
		return "redirect:/gestion/usuario/listar";
	}
	@GetMapping({"/editar/{id}"})
	public String editar(@PathVariable(name = "id")Long id, Model model, Principal principal, Authentication auth, RedirectAttributes flash) {
		Usuario u = usuarioService.findById(id);
		model.addAttribute("title","lista de usuarios");
		model.addAttribute("myUser",u.getUsuarioRegistrable());
		model.addAttribute("users", usuarioService.findAll());
		model.addAttribute("roles",rolService.findAll());
		model.addAttribute("fechaSistema",new SimpleDateFormat("dd/MMMM/yyyy").format(new Date()));
		return "usuario/lista";
	}
	
	@PostMapping({"/editar"})
	public String editar(@Valid @ModelAttribute(name = "myUser")UsuarioRegistrable u, BindingResult result,
						 Model model, Principal principal, 
						 Authentication auth, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("title","lista de usuarios");
			model.addAttribute("users", usuarioService.findAll());
			model.addAttribute("roles", rolService.findAll());
			model.addAttribute("fechaSistema",new SimpleDateFormat("dd/MMMM/yyyy").format(new Date()));
			return "usuario/lista";
		}
		if(u.getRolId()==null) {
			model.addAttribute("title","lista de usuarios");
			model.addAttribute("users", usuarioService.findAll());
			model.addAttribute("roles", rolService.findAll());
			model.addAttribute("errorRol", "Seleccione uno");
			model.addAttribute("fechaSistema",new SimpleDateFormat("dd/MMMM/yyyy").format(new Date()));
			return "usuario/lista";
		}
		
		usuarioService.save(u);
		
		return "redirect:/gestion/usuario/listar";
	}
}
