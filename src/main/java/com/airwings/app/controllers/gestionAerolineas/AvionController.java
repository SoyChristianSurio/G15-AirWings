package com.airwings.app.controllers.gestionAerolineas;

import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.avion.Avion;
import com.airwings.app.model.entity.avion.Clase;
import com.airwings.app.services.AerolineaService;
import com.airwings.app.services.avion.AsientoService;
import com.airwings.app.services.avion.AvionService;
import com.airwings.app.services.avion.ClaseService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AvionController {
    @Autowired
    private AvionService avionService;
    
    @Autowired
    private ClaseService claseService;
    
    @Autowired
    private AsientoService asientoService;
    
    @Autowired
    private AerolineaService areolineaService;
    
    @GetMapping("/avion/lista")
    public String acionLista(Model model){
        List<Avion> aviones= avionService.listaAvion();
        model.addAttribute("aviones", aviones);
        return "avion/lista";
    }
    
    @GetMapping("/avion/crear")
    public String avionLista(Model model){
        List<Clase> clases= claseService.listaClase();
        List<Aerolinea> aerolineas= areolineaService.findAll();
        model.addAttribute("clases", clases);
        model.addAttribute("aerolineas", aerolineas);
        return "avion/crear";
    }
}
