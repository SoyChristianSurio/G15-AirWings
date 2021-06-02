package com.airwings.app.controllers.gestion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion")
public class GestionParametrizablesController {
	
	@GetMapping("/parametrizables")
	public String listaParametrizables() {
		
		
		
		return "";
	}
}
