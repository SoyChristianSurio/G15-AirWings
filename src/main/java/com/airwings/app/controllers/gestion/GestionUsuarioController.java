package com.airwings.app.controllers.gestion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion/usuario")
public class GestionUsuarioController {

	@GetMapping("/listar")
	public String listarTodos(Model model) {
		
		model.addAttribute("title","lista de usuarios");
		
		return "usuario/listar_usuarios";
	}
	
}
