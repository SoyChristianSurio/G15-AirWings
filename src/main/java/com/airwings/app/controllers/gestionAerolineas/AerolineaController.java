package com.airwings.app.controllers.gestionAerolineas;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airwings.app.model.DTO.aerolinea.AerolineaDto;
import com.airwings.app.model.DTO.aeropuerto.AeropuertoDto2;
import com.airwings.app.model.entity.usuario.AdminAerolinea;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;
import com.airwings.app.model.entity.usuario.Usuario;
import com.airwings.app.services.AerolineaService;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.CiudadService;
import com.airwings.app.services.PaisService;
import com.airwings.app.services.usuario.AdminAerolineaService;
import com.airwings.app.services.usuario.AdminAeropuertoService;
import com.airwings.app.services.usuario.UsuarioService;

@Controller
@RequestMapping("/gestion/aerolinea")
public class AerolineaController {
	
	@Autowired
	AerolineaService aerolService;
	@Autowired
	PaisService paisService;
//	@Autowired
//	CiudadService ciudadService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	AdminAerolineaService adminAerolService;
	
	@Secured("ROLE_admin")// aeropuerto_listar
	@GetMapping({"/lista","/","/guardar","/editar","/eliminar"})
	public String lista(Model model) {
		model.addAttribute("aerols", aerolService.findAll());
		model.addAttribute("paises", paisService.findAll());		
		model.addAttribute("newAerol", new AerolineaDto());
		return "/aerolinea/lista";
	}

	
	
	@GetMapping("/editar/{id}")
	public String guardar(@PathVariable(name = "id")Long id, Model model, RedirectAttributes flash) {
		if( aerolService.findById(id)==null ) {
			flash.addFlashAttribute("warning"," No existe esa aerolinea");
			return "redirect:/gestion/aerolinea/lista";
		}
		AerolineaDto a = aerolService.findById(id).toAerolineaDto();
		model.addAttribute("myAerol", a);
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("ciudadese", paisService.findById( a.getPaisId() ).getCiudades());
		model.addAttribute("aerops", aerolService.findAll());
		model.addAttribute("newAerop", new AerolineaDto());
		return "/aerolinea/lista";
	}
	/*
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("delAerop", aeropService.findById(id));
		model.addAttribute("aerops", aeropService.findAll());
		model.addAttribute("paises", paisService.findAll());		
		model.addAttribute("newAerop", new AeropuertoDto2());
		model.addAttribute("msgDelete", adminAeropService.findAllByAeropuerto(aeropService.findById(id)).size()+" administradores dejarán de serlo");
		return "/aeropuerto/lista";
	}
	*/ 
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("newAerol")AerolineaDto aerol, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Crear:"+aerol.getCodigo()+" "+aerol.getNombreCorto()+" "+aerol.getPaisId());
		if(result.hasErrors()) {
			model.addAttribute("aerols", aerolService.findAll());
			model.addAttribute("paises", paisService.findAll());
			if(aerol.getPaisId()!=null)model.addAttribute("ciudades", paisService.findById(aerol.getPaisId()).getCiudades());
			model.addAttribute("errorCrear","");
			return "/aerolinea/lista";
		}
		System.out.println("guardando nueva aerolinea");
		aerolService.save(aerol);
		flash.addFlashAttribute("info","Creada aerolinea "+aerol.getNombreCorto()+" "+aerol.getCodigo()+" en "+paisService.findById(aerol.getPaisId()).getNombre()+" exitósamente");
		return "redirect:/gestion/aerolinea/lista";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid @ModelAttribute("myAerol")AerolineaDto aerol, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Crear:"+aerol.getCodigo()+" "+aerol.getNombreCorto()+" "+aerol.getPaisId());
		if(result.hasErrors()) {			
			model.addAttribute("aerols", aerolService.findAll());
			model.addAttribute("newAerol", new AerolineaDto());
			model.addAttribute("paises", paisService.findAll());
			if(aerol.getPaisId()!=null)model.addAttribute("ciudades", paisService.findById(aerol.getPaisId()).getCiudades());
			return "/aerolinea/lista";
		}
		System.out.println("Editando aeropuerto");
		aerolService.save(aerol);
		flash.addFlashAttribute("info","Editado con éxito");
		return "redirect:/gestion/aerolinea/lista";
	}
	/*
	@PostMapping("/eliminar")
	public String eliminar(@ModelAttribute("myAerop")AeropuertoDto2 aerop, Model model, RedirectAttributes flash) {
		System.out.println("Crear:"+aerop.getCodigo()+" "+aerop.getNombre()+" "+aerop.getPaisId());
		model.addAttribute("aerops", aeropService.findAll());
		model.addAttribute("paises", paisService.findAll());		
		model.addAttribute("newAerop", new AeropuertoDto2());
		System.out.println("Eliminando aeropuerto");
		
		aeropService.deleteById(aerop.getId());
		flash.addFlashAttribute("warning","TRREMENEEEDO Eliminado con éxito");
		return "redirect:/gestion/aeropuerto/lista";
	}
	*/
	@GetMapping("/{id}/admins")
	public String admins(@PathVariable("id")Long id, Model model) {
		model.addAttribute("adminsal", usuarioService.findAllAdminNotOfAerol(id));
		model.addAttribute("aerol", aerolService.findById(id) );
		model.addAttribute("aerolAdmins", usuarioService.findAllAdminOfAerol(id));
		return "/aerolinea/admins";
	}
	
	@GetMapping("/{id}/setadmin/{ida}")
	public String asignarAdmin(@PathVariable("id")Long id, @PathVariable("ida")Long ida, Model model, RedirectAttributes flash) {
		System.out.println("-------------------------------------------------------");
		System.out.println(usuarioService.findUserAdminOfAerol(ida, id));
		System.out.println("-------------------------------------------------------");
		if(usuarioService.findUserAdminOfAerol(ida, id)!=null) {
			flash.addFlashAttribute("info","El usuario YA era administrador de este aeropuerto");
			return "redirect:/gestion/aeropuerto/"+id+"/admins";
		}		
		AdminAerolinea a = new AdminAerolinea();
		a.setUsuario(usuarioService.findById(ida));
		a.setAerolinea(aerolService.findById(id));
		adminAerolService.save(a);
		flash.addFlashAttribute("success","Se agregó un nuevo administrador a esta aerolinea");
		return "redirect:/gestion/aerolinea/"+id+"/admins";
	}
	@GetMapping("/{id}/deladmin/{ida}")
	public String desasignarAdmin(@PathVariable("id")Long id, @PathVariable("ida")Long ida, Model model, RedirectAttributes flash) {
		adminAerolService.deleteById(adminAerolService.getRegistroAdminAerop(ida, id));
		
		flash.addFlashAttribute("warning","Se eliminó un administrador a esta aerolinea");
		return "redirect:/gestion/aerolinea/"+id+"/admins";
	}
	
}

