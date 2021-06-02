package com.airwings.app.controllers.gestionAerolineas;

import com.airwings.app.model.entity.avion.Asiento;
import com.airwings.app.services.avion.AsientoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AsientoController {

    @Autowired
    private AsientoService asientoService;
    
    @GetMapping("/avion/asiento/lista")
    public String asientoLista(Model model){
        List<Asiento> asientos= asientoService.listaAsiento();
        model.addAttribute("asientos", asientos);
        return "avion/asiento/lista";
    }

}
