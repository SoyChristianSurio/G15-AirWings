package com.airwings.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.airwings.app.services.CiudadService;
import com.airwings.app.services.PaisService;

@RestController
public class ApiPaisController {
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("/api/ciudad/{id}")
	public ResponseEntity<?> ciudadFindAll(@PathVariable(name = "id")Long id){
		return ResponseEntity.ok().body(paisService.findById(id).getCiudades());
	}
	
	@GetMapping("/api/pais")
	public ResponseEntity<?> paisFindAll(){
		return ResponseEntity.ok().body(paisService.findAll());
	}
	
}
