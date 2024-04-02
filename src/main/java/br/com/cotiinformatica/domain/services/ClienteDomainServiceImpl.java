package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.AutenticarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.AutenticarClienteResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteResponseDto;
import br.com.cotiinformatica.domain.entities.Cliente;
import br.com.cotiinformatica.domain.interfaces.ClienteDomainService;
import br.com.cotiinformatica.infrastructure.components.SHA256Component;
import br.com.cotiinformatica.infrastructure.components.TokenComponent;
import br.com.cotiinformatica.infrastructure.repositories.ClienteRepository;

@Service
public class ClienteDomainServiceImpl implements ClienteDomainService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SHA256Component sha256Component;

	@Autowired
	private TokenComponent tokenComponent;

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
		cliente.setSenha(sha256Component.criptografarSHA256(dto.getSenha()));

		clienteRepository.save(cliente);

		// Retornar os dados da reposta
		CriarClienteResponseDto response = modelMapper.map(cliente, CriarClienteResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public AutenticarClienteResponseDto autenticarCliente(AutenticarClienteRequestDto dto) {

		// Buscar o cliente no banco de dados
		Cliente cliente = clienteRepository.findByEmailAndSenha(dto.getEmail(),
				sha256Component.criptografarSHA256(dto.getSenha()));

		// Verificando se o cliente foi encontrado
		if (cliente != null) {

			// Copiando os dados do cliente para o objeto DTO de resposta
			AutenticarClienteResponseDto response = modelMapper.map(cliente, AutenticarClienteResponseDto.class);
			response.setDataHoraAcesso(new Date());

			try {
				response.setToken(tokenComponent.generateToken(cliente.getEmail()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return response;
		} else {
			throw new IllegalAccessError("Acesso negado. Usuário não encontrado.");
		}
	}

}
