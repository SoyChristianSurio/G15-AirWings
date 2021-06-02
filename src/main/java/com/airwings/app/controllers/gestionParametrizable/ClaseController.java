
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
    public String guardar(@Valid Clase clase, Errors errores){
        if(errores.hasErrors()){
            return "/avion/clase/lista";
        }
        claseService.guardar(clase);
        return "redirect:/avion/clase/lista";
    }
}
