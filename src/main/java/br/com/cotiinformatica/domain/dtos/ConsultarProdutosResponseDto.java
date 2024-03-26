package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ConsultarProdutosResponseDto {

	private UUID id;
	private String nome;
	private Double preco;
	private byte[] foto;
}
