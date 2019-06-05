package com.manejoerrores.springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.manejoerrores.springboot.exception.ProjectException;

@Controller
public class ExceptionController {

	@GetMapping("/exception")
	public String exception() throws ProjectException {
		throw new ProjectException(HttpStatus.BAD_GATEWAY, "Prueba de excepcion");
	}
	
	@GetMapping("/exception500")
	public String exception500() throws Exception {
		throw new Exception("Error 500 desde el controlador");
	}
}
