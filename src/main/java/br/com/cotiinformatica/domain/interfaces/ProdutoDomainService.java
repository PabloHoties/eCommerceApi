package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.ConsultarProdutosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoResponseDto;

public interface ProdutoDomainService {

	// Serviço para cadastro de produto
	CriarProdutoResponseDto criarProduto(CriarProdutoRequestDto dto);
	
	// Serviço para consulta de produtos
	List<ConsultarProdutosResponseDto> consultarProdutos();
	
	// Serviço para consulta de um produto
	ConsultarProdutosResponseDto obterProduto(UUID id);
}
