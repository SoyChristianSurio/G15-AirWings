package com.airwings.app.controllers.gestionAeropuertos;

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

import com.airwings.app.model.DTO.aeropuerto.AeropuertoDto2;
import com.airwings.app.model.entity.usuario.AdminAeropuerto;
import com.airwings.app.services.AeropuertoService;
import com.airwings.app.services.CiudadService;
import com.airwings.app.services.PaisService;
import com.airwings.app.services.usuario.AdminAeropuertoService;
import com.airwings.app.services.usuario.UsuarioService;


@Controller
@RequestMapping("/gestion/aeropuerto")
public class AeropuertoController {

	@Autowired
	AeropuertoService aeropService;
	@Autowired
	PaisService paisService;
	@Autowired
	CiudadService ciudadService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	AdminAeropuertoService adminAeropService;
	
	@Secured("ROLE_admin")// aeropuerto_listar
	@GetMapping({"/lista","/","/guardar"})
	public String lista(Model model) {
		model.addAttribute("aerops", aeropService.findAll());
		model.addAttribute("paises", paisService.findAll());		
		model.addAttribute("newAerop", new AeropuertoDto2());
		return "/aeropuerto/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String guardar(@PathVariable(name = "id")Long id, Model model) {
		AeropuertoDto2 a = aeropService.findById(id).toAeropuertoDto2();
		model.addAttribute("myAerop", a);
		model.addAttribute("paises", paisService.findAll());
		model.addAttribute("ciudadese", paisService.findById( a.getPaisId() ).getCiudades());
		model.addAttribute("aerops", aeropService.findAll());
		model.addAttribute("newAerop", new AeropuertoDto2());
		return "/aeropuerto/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("delAerop", aeropService.findById(id));
		model.addAttribute("aerops", aeropService.findAll());
		model.addAttribute("paises", paisService.findAll());		
		model.addAttribute("newAerop", new AeropuertoDto2());
		model.addAttribute("msgDelete", adminAeropService.findAllByAeropuerto(aeropService.findById(id)).size()+" administradores dejarán de serlo");
		return "/aeropuerto/lista";
	}
	
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("newAerop")AeropuertoDto2 aerop, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Crear:"+aerop.getCodigo()+" "+aerop.getNombre()+" "+aerop.getPaisId());
		if(result.hasErrors()) {
			model.addAttribute("aerops", aeropService.findAll());
			model.addAttribute("paises", paisService.findAll());
			if(aerop.getPaisId()!=null)model.addAttribute("ciudades", paisService.findById(aerop.getPaisId()).getCiudades());
			model.addAttribute("errorCrear","");
			return "/aeropuerto/lista";
		}
		System.out.println("guardando nuevo aeropuerto");
		aeropService.save(aerop);
		flash.addFlashAttribute("info","Creado aeropuerto "+aerop.getNombre()+" "+aerop.getCodigo()+" en "+paisService.findById(aerop.getPaisId()).getNombre()+" exitósamente");
		return "redirect:/gestion/aeropuerto/lista";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid @ModelAttribute("myAerop")AeropuertoDto2 aerop, BindingResult result, Model model, RedirectAttributes flash) {
		System.out.println("Crear:"+aerop.getCodigo()+" "+aerop.getNombre()+" "+aerop.getPaisId());
		if(result.hasErrors()) {			
			model.addAttribute("aerops", aeropService.findAll());
			model.addAttribute("newAerop", new AeropuertoDto2());
			model.addAttribute("paises", paisService.findAll());
			if(aerop.getPaisId()!=null)model.addAttribute("ciudades", paisService.findById(aerop.getPaisId()).getCiudades());
			return "/aeropuerto/lista";
		}
		System.out.println("Editando aeropuerto");
		aeropService.save(aerop);
		flash.addFlashAttribute("info","TRREMENEEEDO Editado con éxito");
		return "redirect:/gestion/aeropuerto/lista";
	}
	
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
	
	@GetMapping("/{id}/admins")
	public String admins(@PathVariable("id")Long id, Model model) {
		model.addAttribute("adminsap", usuarioService.findAllAdminNotOfAerop(id));
		model.addAttribute("aerop", aeropService.findById(id) );
		model.addAttribute("aeropAdmins", usuarioService.findAllAdminOfAerop(id));
		return "/aeropuerto/admins";
	}
	
	@GetMapping("/{id}/setadmin/{ida}")
	public String asignarAdmin(@PathVariable("id")Long id, @PathVariable("ida")Long ida, Model model, RedirectAttributes flash) {
		System.out.println("-------------------------------------------------------");
		System.out.println(usuarioService.findUserAdminOfAerop(ida, id));
		System.out.println("-------------------------------------------------------");
		if(usuarioService.findUserAdminOfAerop(ida, id)!=null) {
			flash.addFlashAttribute("info","El usuario YA era administrador de este aeropuerto");
			return "redirect:/gestion/aeropuerto/"+id+"/admins";
		}		
		AdminAeropuerto a = new AdminAeropuerto();
		a.setUsuario(usuarioService.findById(ida));
		a.setAeropuerto(aeropService.findById(id));
		adminAeropService.save(a);
		flash.addFlashAttribute("success","Se agregó un nuevo administrador a este aeropuerto");
		return "redirect:/gestion/aeropuerto/"+id+"/admins";
	}
	@GetMapping("/{id}/deladmin/{ida}")
	public String desasignarAdmin(@PathVariable("id")Long id, @PathVariable("ida")Long ida, Model model, RedirectAttributes flash) {
		adminAeropService.deleteById(adminAeropService.getRegistroAdminAerop(ida, id));
		
		flash.addFlashAttribute("warning","Se eliminó un administrador a este aeropuerto");
		return "redirect:/gestion/aeropuerto/"+id+"/admins";
	}
}




























