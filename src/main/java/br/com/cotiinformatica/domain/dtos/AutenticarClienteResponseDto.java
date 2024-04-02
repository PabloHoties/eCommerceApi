package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class AutenticarClienteResponseDto {

	private UUID id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String token;
	private Date dataHoraAcesso;
}
