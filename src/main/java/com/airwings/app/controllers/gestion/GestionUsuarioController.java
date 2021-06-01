package com.airwings.app.controllers.gestion;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/usuario")
public class GestionUsuarioController {

	@Secured("ROLE_admin")
	@GetMapping("/listar")
	public String listarTodos(Model model, Principal principal, Authentication auth) {
		
		model.addAttribute("title","lista de usuarios");
		
		return "usuario/listar_usuarios";
	}
	
}
