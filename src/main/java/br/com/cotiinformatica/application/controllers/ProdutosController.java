package br.com.cotiinformatica.application.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.domain.dtos.ConsultarProdutosResponseDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarProdutoResponseDto;
import br.com.cotiinformatica.domain.interfaces.ProdutoDomainService;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDomainService produtoDomainService;

	@PostMapping(value = "criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CriarProdutoResponseDto> criar(@RequestParam("nome") String nome,
			@RequestParam("preco") Double preco, @RequestParam("foto") MultipartFile foto) throws Exception {

		// Verificar o tipo do arquivo enviado
		String tipo = foto.getContentType();

		// Verificar se o aquivo é uma foto
		if (tipo.equals("image/jpeg") || tipo.equals("image/jpg") || tipo.equals("image/png")) {

			// Capturar os dados do produto
			CriarProdutoRequestDto dto = new CriarProdutoRequestDto();
			dto.setNome(nome);
			dto.setPreco(BigDecimal.valueOf(preco));
			dto.setFoto(foto.getBytes());

			// Cadastrar o produto
			CriarProdutoResponseDto response = produtoDomainService.criarProduto(dto);

			// HTTP 201 (CREATED)
			return ResponseEntity.status(201).body(response);
		} else {
			throw new IllegalArgumentException("Erro. A foto deve ser do tipo jpeg, jpg, ou png.");
		}

	}

	@GetMapping("consultar")
	public List<ConsultarProdutosResponseDto> consultar() {
		return produtoDomainService.consultarProdutos();
	}

	@GetMapping("obter/{id}")
	public ConsultarProdutosResponseDto obter(@PathVariable("id") UUID id) {
		return produtoDomainService.obterProduto(id);
	}
}
