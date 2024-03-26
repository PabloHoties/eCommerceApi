package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarClienteResponseDto {

	private UUID id;
	private String nome;
	private String email;
	private String cpf;
	private Date dataHoraCadastro;
}
