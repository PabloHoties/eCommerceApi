package br.com.cotiinformatica.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.CriarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteResponseDto;
import br.com.cotiinformatica.domain.interfaces.ClienteDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClientesController {

	@Autowired
	private ClienteDomainService clienteDomainService;

	@PostMapping("criar")
	public ResponseEntity<CriarClienteResponseDto> criar(@RequestBody @Valid CriarClienteRequestDto dto) {

		// Executando o serviço da camada de domínio
		CriarClienteResponseDto response = clienteDomainService.criarCliente(dto);

		// Retornar a reposta
		return ResponseEntity.status(201).body(response);
	}

	@PostMapping("autenticar")
	public void autenticar() {
		// TODO
	}
}
