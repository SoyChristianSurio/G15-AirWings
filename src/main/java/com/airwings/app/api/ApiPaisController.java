package com.airwings.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airwings.app.services.CiudadService;
import com.airwings.app.services.PaisService;

@RestController
public class ApiPaisController {
	
	@Autowired
	private PaisService paisService;
	@Autowired
	private CiudadService ciudadService;
	
	@GetMapping("/api/ciudad")
	public ResponseEntity<?> ciudadFindAll(){
		return ResponseEntity.ok().body(ciudadService.findAll());
	}
	
	@GetMapping("/api/pais")
	public ResponseEntity<?> paisFindAll(){
		return ResponseEntity.ok().body(paisService.findAll());
	}
}
