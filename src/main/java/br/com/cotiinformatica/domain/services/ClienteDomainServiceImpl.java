package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.CriarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteResponseDto;
import br.com.cotiinformatica.domain.entities.Cliente;
import br.com.cotiinformatica.domain.interfaces.ClienteDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ClienteRepository;

@Service
public class ClienteDomainServiceImpl implements ClienteDomainService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CriarClienteResponseDto criarCliente(CriarClienteRequestDto dto) {

		// Verificar se já existe um cliente com o email informado
		if (clienteRepository.findByEmail(dto.getEmail()) != null) {
			throw new IllegalArgumentException("O email informado já está cadastrado.");
		}

		// Verificar se já existe um cliente com o cpf informado
		if (clienteRepository.findByCpf(dto.getCpf()) != null) {
			throw new IllegalArgumentException("O CPF informado já está cadastrado.");
		}

		// Preencher os dados do cliente
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		cliente.setId(UUID.randomUUID());

		clienteRepository.save(cliente);

		// Retornar os dados da reposta
		CriarClienteResponseDto response = modelMapper.map(cliente, CriarClienteResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

}
