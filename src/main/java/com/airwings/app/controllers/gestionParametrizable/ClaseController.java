
package com.airwings.app.controllers.gestionParametrizable;

import com.airwings.app.model.entity.avion.Clase;
import com.airwings.app.services.avion.ClaseService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@Slf4j
public class ClaseController {
    @Autowired
    private ClaseService claseService;
    
    @GetMapping("/avion/clase/lista")
    public String claseLista(Model model){
        List<Clase> clases= claseService.listaClase();
        model.addAttribute("clases", clases);
        return "avion/clase/lista";
    }
    
    @PostMapping("/avion/clase/crear")
    //@valid Persona persona, Errors errores ---- siempre deben de estar juntos
    //si se quiere añadir otro parametro, debe ir despues
    public String guardar(@Valid Clase clase, Errors errores, RedirectAttributes ra){
        if(errores.hasErrors()){
            return "/avion/clase/lista";
        }
        String msg;
        if(clase.getId()!=null){
            msg="Clase actualizada con éxito.";
        } else {
            msg="Clase creada con éxito.";
        }
        claseService.guardar(clase);
        ra.addFlashAttribute("success",msg);
        return "redirect:/avion/clase/lista";
    }
    
    @GetMapping("/avion/clase/editar/{id}")
    public String editar(Clase clase, Model model){
        clase=claseService.encontrarClase(clase);
        model.addAttribute("clase", clase);
        return "avion/clase/edit_modal";
    }
    
    @GetMapping("/avion/clase/vista/eliminar/{id}")
    public String eliminarVista(Clase clase, Model model){
        clase=claseService.encontrarClase(clase);
        model.addAttribute("clase", clase);
        return "avion/clase/delete_modal";
    }
    
    @GetMapping("/avion/clase/eliminar/{id}")
    public String eliminar(Clase clase, RedirectAttributes ra){
        claseService.eliminar(clase);
        ra.addFlashAttribute("success", "Clase eliminada con éxito.");
        return "redirect:/avion/clase/lista";
    }
}
