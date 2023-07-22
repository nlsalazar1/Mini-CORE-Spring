package com.udla.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//Metodo para llamar a nuetra pagina Home.html
	@GetMapping({"/","/home","/index"})
	public String index() {
		
		return "home";
	}
}
