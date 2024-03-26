package br.com.cotiinformatica.domain.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CriarProdutoRequestDto {

	private String nome;
	
	private BigDecimal preco;
	
	private byte[] foto;
}
