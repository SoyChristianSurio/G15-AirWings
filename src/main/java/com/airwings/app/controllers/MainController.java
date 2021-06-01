package com.airwings.app.controllers;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airwings.app.model.DTO.usuario.UsuarioRegistrable;
import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.usuario.UsuarioService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public String inicio(Model model, Principal principal, Authentication auth) {
		if(principal==null) return "redirect:/login";
		Usuario usuario = usuarioService.findByUsername(auth.getName());
		
		
		//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| Inicio admin de aerolinea
		if(hasRol("ROLE_admin")) {
			model.addAttribute("title", "administración");
			return "index";
		}
		//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| Inicio admin de aerolinea
		if(hasRol("ROLE_admin_aerolinea")) {
			model.addAttribute("title", "administración de aerolinea");
			return "inicio_aerolinea";
		}
		//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| Inicio admin de aeropuerto
		if(hasRol("ROLE_admin_aeropuerto")) {
			model.addAttribute("title", "administración de aerolinea");
			return "inicio_aeropuerto";
		}
		//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| Inicio usuario normal
		
		if(!usuario.getRegistroCompleto()) {
			if(usuario.getClienteNatural()) return "redirect:/usuario/persona/datos";
			else return "redirect:/usuario/empresa/datos";
		}
		
		
		if(hasRol("ROLE_user")) {
			model.addAttribute("title", "inicio");
			return "redirect:/usuario/inicio";
		}
		
		model.addAttribute("title", "Login");
		return "login";
	}
	//##################################################################################################################################
	@GetMapping("login")
	public String login(@RequestParam(name = "error",required = false)String error,
						@RequestParam(name = "logout",required = false)String logout,
						Model model, Principal principal, RedirectAttributes flash,
						Authentication auth) {
		
		if(principal!=null) {  // si ya tiene la sesión iniciada, mandar a inicio
			flash.addFlashAttribute("info", "Ya ha iniciado sesión");
			return "redirect:/"; 
		}
		if(logout!=null) model.addAttribute("info", "Se ha cerrado sesión");
		
		/*  Cuando hay un error al iniciar sesión, SpringSecurity redirecciona a /login y añade un parametro en la url: ?error
		 *  con @RequestParam(name= "error") recuperamos el error en la variable String error. el required=false indica que no 
		 *  siempre se espera dicho parametro (porque cuando no hay error, el parametro no existe). 
		 */
		if(error!=null) model.addAttribute("error", "No se pudo iniciar sesión: usuario o contraseña incorrectos");
		model.addAttribute("title", "Login");
		return "login";
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
	
	public boolean hasRol(String rol) {
		SecurityContext context = SecurityContextHolder.getContext();
		if(context==null) return false;
		
		Authentication auth = context.getAuthentication();
		if(auth==null) return false;
		
		Collection<? extends GrantedAuthority>authorities = auth.getAuthorities();
		for(GrantedAuthority a : authorities) {
			if(rol.equals(a.getAuthority())) return true;
		}
		return false;
	}
	
	
}
