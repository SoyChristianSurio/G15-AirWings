package com.airwings.app.controllers.gestionUsuarios;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airwings.app.services.usuario.UsuarioService;

@Controller
@RequestMapping("/gestion/usuario")
public class GestionUsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Secured("ROLE_admin")
	@GetMapping({"/listar","/"})
	public String listarTodos(Model model, Principal principal, Authentication auth) {
		model.addAttribute("title","lista de usuarios");
		model.addAttribute("users", usuarioService.findAll());
		model.addAttribute("fechaSistema",new SimpleDateFormat("dd/MMMM/yyyy").format(new Date()));
		return "usuario/lista";
	}
	
}
