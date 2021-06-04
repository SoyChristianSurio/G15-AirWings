package com.airwings.app.controllers.gestionParametrizable;

import com.airwings.app.model.entity.avion.TipoAvion;
import com.airwings.app.services.avion.TipoAvionService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class TipoAvionController {

    @Autowired
    private TipoAvionService tipoAvionService;

    @GetMapping("/avion/tipoAvion/lista")
    public String tipoAvionLista(Model model) {
        List<TipoAvion> tipoAviones = tipoAvionService.listaTipoAvion();
        model.addAttribute("tipoAviones", tipoAviones);
        return "avion/tipoAvion/lista";
    }

    @PostMapping("/avion/tipoAvion/crear")
    public String guardar(@Valid TipoAvion tipoAvion, Errors errores, RedirectAttributes ra) {
        if (errores.hasErrors()) {
            return "/avion/tipoAvion/lista";
        }
        String msg;
        if (tipoAvion.getId() != null) {
            msg = "Tipo avion actualizado con éxito.";
        } else {
            msg = "Tipo avion creado con éxito.";
        }
        tipoAvionService.guardar(tipoAvion);
        ra.addFlashAttribute("success", msg);
        return "redirect:/avion/tipoAvion/lista";
    }

    @GetMapping("/avion/tipoAvion/editar/{id}")
    public String editar(TipoAvion tipoAvion, Model model) {
        tipoAvion = tipoAvionService.encontrarTipoAvion(tipoAvion);
        model.addAttribute("tipoAvion", tipoAvion);
        return "avion/tipoAvion/edit_modal";
    }

    @GetMapping("/avion/tipoAvion/vista/eliminar/{id}")
    public String eliminarVista(TipoAvion tipoAvion, Model model) {
        tipoAvion = tipoAvionService.encontrarTipoAvion(tipoAvion);
        model.addAttribute("tipoAvion", tipoAvion);
        return "avion/tipoAvion/delete_modal";
    }

    @GetMapping("/avion/tipoAvion/eliminar/{id}")
    public String eliminar(TipoAvion tipoAvion, RedirectAttributes ra) {
        tipoAvionService.eliminar(tipoAvion);
        ra.addFlashAttribute("success", "Tipo de avion eliminado con éxito.");
        return "redirect:/avion/tipoAvion/lista";
    }
}
