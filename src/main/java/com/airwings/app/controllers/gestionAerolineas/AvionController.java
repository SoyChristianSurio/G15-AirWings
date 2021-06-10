package com.airwings.app.controllers.gestionAerolineas;

import com.airwings.app.model.DAO.avion.AsientoDao;
import com.airwings.app.model.entity.Aerolinea;
import com.airwings.app.model.entity.avion.Asiento;
import com.airwings.app.model.entity.avion.Avion;
import com.airwings.app.model.entity.avion.Clase;
import com.airwings.app.model.entity.avion.TipoAvion;
import com.airwings.app.services.AerolineaService;
import com.airwings.app.services.avion.AsientoService;
import com.airwings.app.services.avion.AvionService;
import com.airwings.app.services.avion.ClaseService;
import com.airwings.app.services.avion.TipoAvionService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private TipoAvionService tipoAvionService;

    @GetMapping("/avion/lista")
    public String acionLista(Model model) {
        List<Avion> aviones = avionService.listaAvion();
        model.addAttribute("aviones", aviones);
        return "avion/lista";
    }

    @GetMapping("/avion/crear")
    public String avionLista(Model model) {
        List<Clase> clases = claseService.listaClase();
        List<Aerolinea> aerolineas = areolineaService.findAll();
        List<TipoAvion> tipoAviones = tipoAvionService.listaTipoAvion();
        model.addAttribute("clases", clases);
        model.addAttribute("aerolineas", aerolineas);
        model.addAttribute("tipoAviones", tipoAviones);
        return "avion/crear";
    }

    @PostMapping("/avion/crear")
    public String guardar(Avion avion, HttpServletRequest request, RedirectAttributes ra) {
        List<Clase> clases = claseService.listaClase();
        
        Avion avion1=avionService.guardar(avion);
        for (Clase clase : clases) {
            Asiento asiento = new Asiento();
            String existe = String.valueOf(clase.getId());
            String claseExiste = request.getParameter("clase" + existe);

            if (claseExiste == null ) {
                claseExiste = "";
            }
            if (claseExiste.equals("on")) {
                String cadena = String.valueOf(clase.getId());
                asiento.setCantidadAsiento(Integer.valueOf(request.getParameter("ca"+cadena)));
                Double doble=Double.valueOf(request.getParameter("p"+cadena));
                System.out.println("double:"+doble);
                asiento.setPrecioAsiento(Double.valueOf(request.getParameter("p"+cadena)));
                asiento.setAvion(avion1);
                asiento.setClase(clase);
                asientoService.guardar(asiento);
            }
        }
        
        String msg = "Tipo avion creado con éxito.";
        ra.addFlashAttribute("success", msg);
        return "redirect:/avion/lista";
    }
    
    @GetMapping("/avion/editar/{id}")
    public String avionEditar(Avion avion,Model model) {
        avion=avionService.encontrarAvion(avion);
        model.addAttribute("avion", avion);
        List<Clase> clasesGenerales=claseService.listaClase();
        List<Clase> clasesGenerales2=claseService.listaClase();
        List<Asiento> clasesSeleccionadas=avion.getAsientos();  
        for (Clase clase : clasesGenerales) {
            for (Asiento asiento : clasesSeleccionadas){
                if(clase==asiento.getClase()){
                    clasesGenerales2.remove(clase);
                }
            }
        }
        
        
        
        List<Clase> clases = claseService.listaClase();
        List<Aerolinea> aerolineas = areolineaService.findAll();
        List<TipoAvion> tipoAviones = tipoAvionService.listaTipoAvion();
        model.addAttribute("clases", clases);
        model.addAttribute("aerolineas", aerolineas);
        model.addAttribute("tipoAviones", tipoAviones);
        model.addAttribute("clasesGenerales2", clasesGenerales2);
        return "avion/editar";
    }
}
