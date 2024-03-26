package br.com.cotiinformatica.domain.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarProdutoResponseDto {

	private UUID id;
	private String nome;
	private Double preco;
	private Date dataHoraCadastro;
}
