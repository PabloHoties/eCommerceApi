package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarClienteRequestDto {

	@Size(min = 8, max = 150, message = "Por favor, informe o nome de 8 a 150 caracteres.")
	@NotEmpty(message = "Por favor, informe o nome do cliente.")
	private String nome;
	
	@Email(message = "Por favor, informe um endereço de email válido.")
	@NotEmpty(message = "Por favor, informe o email do cliente.")
	private String email;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "Por favor, informe exatamente 11 dígitos numéricos.")
	@NotEmpty(message = "Por favor, informe o cpf do cliente.")
	private String cpf;
}
