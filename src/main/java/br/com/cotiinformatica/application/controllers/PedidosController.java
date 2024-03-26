package br.com.cotiinformatica.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidosController {

	@PostMapping("criar")
	public void criar() {
		// TODO
	}
	
	@GetMapping("consultar")
	public void consultar() {
		// TODO
	}
}
