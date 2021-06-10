package com.airwings.app.controllers.usuario;

import java.security.Principal;

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

import com.airwings.app.model.DAO.usuario.EstadoCivilDao;
import com.airwings.app.model.DAO.usuario.TipoDocumentoDao;
import com.airwings.app.model.DTO.usuario.EmpresaAutoEdit;
import com.airwings.app.model.DTO.usuario.PersonaAutoEdit;
import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.usuario.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	EstadoCivilDao ecDao;
	@Autowired
	TipoDocumentoDao tdDao;
	//##################################################################################################################
	@Secured({"ROLE_user"})
	@GetMapping("/inicio")
	public String inicioUsuario(Model model, Principal principal, Authentication auth, RedirectAttributes flash) {
		if(principal==null) return "redirect:/login";						//si no hay sesión redirecciona al login
		Usuario usuario = usuarioService.findByUsername(auth.getName());	//recuperar de la base al usuario logeado (a veces es util)
		
		/* 	si el registro de datos	no está completo, redirigir según tipo de cliente. */
		//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
		if(!usuario.getRegistroCompleto()) {											 
			flash.addFlashAttribute("warning","Completa la información antes de empezar");
			if(usuario.getClienteNatural()) return "redirect:/usuario/persona/datos";	
			else return "redirect:/usuario/empresa/datos";								
		}//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
		
		
		/* Este bloque se envía para efectos de mostrar y ocultar elementos en el html */
		//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
		if(usuario.getClienteNatural())	model.addAttribute("personaCl"," ");	
		else model.addAttribute("empresaCl"," ");								
		model.addAttribute("menuHome", " ");									
		model.addAttribute( "title", "Inicio");
		//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
		return "usuario/inicio_usuario";
	}
	
	//##################################################################################################################
	@Secured({"ROLE_user","ROLE_admin"})
	@GetMapping({"/persona/datos","/persona/datos/{id}"})
	public String AutoEditPersona(@PathVariable (name = "id", required = false)Long id, Model model, Principal principal, Authentication auth) {
		Usuario usuario;
		if(id==null) {usuario = usuarioService.findByUsername(auth.getName());}
		else {
			usuario = usuarioService.findById(id);
			model.addAttribute("adminEditing",id);}
			
		if(!usuario.getClienteNatural()) {
			if(id==null) {return "redirect:/usuario/empresa/datos";}
			else {return "redirect:/usuario/empresa/datos/"+id;}
		}
		
		if(usuario.getRegistroCompleto()) {
			model.addAttribute("persona", usuarioService.getPersonaAutoEdit( usuario.getNatural() ));
		}
		else model.addAttribute("persona", new PersonaAutoEdit());		
		model.addAttribute( "tiposDocumento", tdDao.findAll());
		model.addAttribute( "estadosCivil", ecDao.findAll() );
		
		model.addAttribute("personaCl"," ");
		model.addAttribute("menuDatos", " ");
		model.addAttribute( "title", "datos de persona");
		
		return "usuario/datos_persona";
	}
	@PostMapping({"/persona/datos","/persona/datos/{id}"})
	public String AutoEditPersonaP( @Valid @ModelAttribute("persona")PersonaAutoEdit persona, BindingResult result, 
									@PathVariable (name = "id", required = false)Long id,Model model, 
									Principal principal, Authentication auth, RedirectAttributes flash) {
		Usuario usuario;
		if(id==null) {usuario = usuarioService.findByUsername(auth.getName());}
		else {
			usuario = usuarioService.findById(id);
			model.addAttribute("adminEditing",id);}
		
		persona.setUsuarioId(usuario.getId());

		if(result.hasErrors()) {
			model.addAttribute( "tiposDocumento", tdDao.findAll());
			model.addAttribute( "estadosCivil", ecDao.findAll() );
			model.addAttribute( "title", "datos de persona");
			return "usuario/datos_persona";
		}
		
		String msg= usuarioService.savePersona(persona);
		model.addAttribute("success",msg);
		if(id==null) return "usuario/inicio_usuario";
		else return "redirect:/gestion/usuario/";
	}
	
	//##################################################################################################################
	@Secured({"ROLE_user","ROLE_admin"})
	@GetMapping({"/empresa/datos","/empresa/datos/{id}"})
	public String AutoEditEmpresa(@PathVariable (name = "id", required = false)Long id, Model model, Principal principal, Authentication auth) {
		Usuario usuario;
		if(id==null) {usuario = usuarioService.findByUsername(auth.getName());}
		else {
			usuario = usuarioService.findById(id);
			model.addAttribute("adminEditing",id);}
		
		if(usuario.getClienteNatural()) {
			if(id==null) {return "redirect:/usuario/persona/datos";}
			else {return "redirect:/usuario/persona/datos/"+id;}
		}
		
		if(usuario.getRegistroCompleto()) {
			model.addAttribute("empresa", usuarioService.getEmpresaAutoEdit( usuario.getEmpresa()));
		}else model.addAttribute("empresa", new EmpresaAutoEdit());
		
		model.addAttribute("empresaCl"," ");
		model.addAttribute("menuDatos", " ");
		model.addAttribute( "title", "datos de la empresa");
		
		return "usuario/datos_empresa";
	}
	@PostMapping({"/empresa/datos","/empresa/datos/{id}"})
	public String AutoEditEmpresaP( @Valid @ModelAttribute("empresa")EmpresaAutoEdit empresa, BindingResult result, 
									@PathVariable (name = "id", required = false)Long id, Model model, 
									Principal principal, Authentication auth, RedirectAttributes flash) {
		Usuario usuario;
		if(id==null) {usuario = usuarioService.findByUsername(auth.getName());}
		else {
			usuario = usuarioService.findById(id);
			model.addAttribute("adminEditing",id);}
		empresa.setUsuarioId(usuario.getId());

		if(result.hasErrors()) {
			model.addAttribute( "title", "datos de la empresa");
			return "usuario/datos_empresa";
		}
		
		String msg= usuarioService.saveEmpresa(empresa);
		model.addAttribute("success",msg);
		model.addAttribute("empresaCl", "");
		
		if(id==null) return "usuario/inicio_usuario";
		else return "redirect:/gestion/usuario/";
	}
}
