package com.airwings.app.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.services.usuario.UsuarioService;

@Controller("/")
public class MainController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public String inicio(Model model) {
		model.addAttribute("title", "Inicio");
		return "index";
	}
	//##################################################################################################################################
	@GetMapping("login")
	public String login(@RequestParam(name = "error",required = false)String error, Model model, Principal principal, RedirectAttributes flash) {
				
		if(principal!=null) {  // si ya tiene la sesión iniciada, mandar a inicio
			flash.addFlashAttribute("info", "Ya ha iniciado sesión");
			return "redirect:/"; 
		}
		
		/*  Cuando hay un error al iniciar sesión, SpringSecurity redirecciona a /login y añade un parametro en la url: ?error
		 *  con @RequestParam(name= "error") recuperamos el error en la variable String error. el required=false indica que no 
		 *  siempre se espera dicho parametro (cuando no hay error, el parametro no existe). 
		 */
		if(error!=null) model.addAttribute("error", "No se pudo iniciar sesión: usuario o contraseña incorrectos");
		model.addAttribute("title", "Login");
		return "login";
	}
	//##################################################################################################################################
	@GetMapping("gestion")
	public String gestion() {
		return "index";
	}
	//##################################################################################################################################
	@GetMapping("inicio")
	public String inicioUsuario(Model model) {
		
		model.addAttribute("title", "inicio");
		return "usuario/inicio_usuario";
	}
	//##################################################################################################################################
	@GetMapping("registro")
	public String registroUsuario(Model model) {
		
		model.addAttribute("usuario", new UsuarioRegistrable());
		model.addAttribute("title", "registro");
		
		return "registro";
	}//----------------------------------------------------------------------------------------------------------------------------------
	
	@PostMapping("registro")
	public String registroUsuarioP(@Valid @ModelAttribute("usuario")UsuarioRegistrable myUsuario, BindingResult result, Model model, RedirectAttributes flash) {
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
		String infoUser = "Usuario; "+myUsuario.getUsername();
		usuarioService.registrar(myUsuario);
		flash.addFlashAttribute("info", infoUser);
		flash.addFlashAttribute("title", "login");
		
		return "redirect:/login";
	}
}
