package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ConsultarProdutosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoResponseDto;
import br.com.cotiinformatica.domain.entities.Produto;
import br.com.cotiinformatica.domain.interfaces.ProdutoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ProdutoRepository;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CriarProdutoResponseDto criarProduto(CriarProdutoRequestDto dto) {

		Produto produto = modelMapper.map(dto, Produto.class);
		produto.setId(UUID.randomUUID());

		produtoRepository.save(produto);

		CriarProdutoResponseDto response = modelMapper.map(produto, CriarProdutoResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}

	@Override
	public List<ConsultarProdutosResponseDto> consultarProdutos() {
		
		// Consultar todos os produtos cadastrados
		List<Produto> produtos = produtoRepository.findAll();
		
		List<ConsultarProdutosResponseDto> response = modelMapper.map
				(produtos, new TypeToken<List<ConsultarProdutosResponseDto>>() {}.getType()); 
		
		return response;
	}

	@Override
	public ConsultarProdutosResponseDto obterProduto(UUID id) {
		
		// Consultar um produto através do ID
		Optional<Produto> produto = produtoRepository.findById(id);
		
		// Verificar se algum produto foi encontrado
		if(produto.isPresent()) {
			
			ConsultarProdutosResponseDto response = modelMapper.map
					(produto, ConsultarProdutosResponseDto.class);
			return response;
		} else {			
			return null;
		}
	}

}
