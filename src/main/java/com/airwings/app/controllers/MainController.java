package com.airwings.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MainController {
	
	@GetMapping
	public String inicio(Model model) {
		model.addAttribute("title", "Inicio");
		return "index";
	}
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}
	
	@GetMapping("gestion")
	public String gestion() {
		return "index";
	}
}
