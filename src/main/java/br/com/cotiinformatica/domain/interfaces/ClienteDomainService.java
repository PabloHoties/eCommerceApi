package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.dtos.AutenticarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarClienteResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteResponseDto;

public interface ClienteDomainService {

	// Serviço para criação de cliente
	CriarClienteResponseDto criarCliente(CriarClienteRequestDto dto);
	
	// Serviço para autenticação de cliente
	AutenticarClienteResponseDto autenticarCliente(AutenticarClienteRequestDto dto);
}
