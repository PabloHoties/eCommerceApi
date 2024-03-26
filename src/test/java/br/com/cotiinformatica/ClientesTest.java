package br.com.cotiinformatica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.domain.dtos.CriarClienteRequestDto;
import br.com.cotiinformatica.domain.dtos.CriarClienteResponseDto;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientesTest {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	
	static String email;
	static String cpf;
	
	@Test
	@Order(1)
	public void criarClienteComSucessoTest() throws Exception {
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarClienteRequestDto dto = new CriarClienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setCpf(faker.number().digits(11));
		
		MvcResult result = mockMvc.perform(post("/api/clientes/criar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpectAll(status().isCreated())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		CriarClienteResponseDto response = objectMapper.readValue
				(content, CriarClienteResponseDto.class);
		
		assertNotNull(response.getId());
		assertEquals(response.getNome(), dto.getNome());
		assertEquals(response.getEmail(), dto.getEmail());
		assertEquals(response.getCpf(), dto.getCpf());
		
		email = dto.getEmail();
		cpf = dto.getCpf();
	}
	@Test
	@Order(2)
	public void criarClienteComEmailInvalidoTest() throws Exception {
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarClienteRequestDto dto = new CriarClienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setEmail(email); //email já cadastrado!
		dto.setCpf(faker.number().digits(11));
		
		MvcResult result = mockMvc.perform(post("/api/clientes/criar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);	
		assertTrue(content.contains("O email informado já está cadastrado."));
	}
	@Test
	@Order(3)
	public void criarClienteComCpfInvalidoTest() throws Exception {
		Faker faker = new Faker(new Locale("pt-BR"));
		
		CriarClienteRequestDto dto = new CriarClienteRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setCpf(cpf); //cpf já cadastrado!
		
		MvcResult result = mockMvc.perform(post("/api/clientes/criar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);	
		assertTrue(content.contains("O CPF informado já está cadastrado."));
	}
	@Test
	@Order(4)
	public void criarClienteComDadosInvalidosTest() throws Exception {
		CriarClienteRequestDto dto = new CriarClienteRequestDto();
		dto.setNome("");
		dto.setEmail("");
		dto.setCpf("");
		
		MvcResult result = mockMvc.perform(post("/api/clientes/criar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpectAll(status().isBadRequest())
				.andReturn();
		
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);	
		assertTrue(content.contains("cpf: Por favor, informe exatamente 11 dígitos numéricos."));
		assertTrue(content.contains("cpf: Por favor, informe o cpf do cliente."));
		assertTrue(content.contains("email: Por favor, informe o email do cliente."));
		assertTrue(content.contains("nome: Por favor, informe o nome de 8 a 150 caracteres."));
		assertTrue(content.contains("nome: Por favor, informe o nome do cliente."));
	}
}
