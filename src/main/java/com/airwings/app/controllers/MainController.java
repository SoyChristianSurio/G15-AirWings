package com.airwings.app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;

@Controller("/")
public class MainController {
	
	@GetMapping
	public String inicio(Model model) {
		model.addAttribute("title", "Inicio");
		return "index";
	}
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}
	
	@GetMapping("gestion")
	public String gestion() {
		return "index";
	}
	
	@GetMapping("inicio")
	public String inicioUsuario(Model model) {
		
		model.addAttribute("title", "inicio");
		return "usuario/inicio_usuario";
	}
	
	@GetMapping("registro")
	public String registroUsuario(Model model) {
		
		model.addAttribute("usuario", new UsuarioRegistrable());
		model.addAttribute("title", "registro");
		
		return "registro";
	}
	
	@PostMapping("registro")
	public String registroUsuarioP(@Valid @ModelAttribute("usuario")UsuarioRegistrable myUsuario, BindingResult result, Model model) {
		System.out.println("es persona: "+myUsuario.getPersona()); 	// BORRAR, SOLO PARA PRUEBAS
		System.out.println("username: "+myUsuario.getUsername());	// BORRAR, SOLO PARA PRUEBAS
		System.out.println("pass1: "+myUsuario.getPass());			// BORRAR, SOLO PARA PRUEBAS
		System.out.println("pass2: "+myUsuario.getPassConfirm());	// BORRAR, SOLO PARA PRUEBAS
		System.out.println("email: "+myUsuario.getEmail());			// BORRAR, SOLO PARA PRUEBAS
		System.out.println("_________________________");			// BORRAR, SOLO PARA PRUEBAS
		if(!myUsuario.getPass().contentEquals(myUsuario.getPassConfirm())) {
			model.addAttribute("passError", "Las contraseñas no coinciden");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("title", "registro");
			return "registro";
		}
		 
		
		model.addAttribute("title", "registro");
		
		return "registro";
	}
}
