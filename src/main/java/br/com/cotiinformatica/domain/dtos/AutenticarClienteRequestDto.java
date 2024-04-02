package br.com.cotiinformatica.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarClienteRequestDto {

	@Email(message = "Por favor, informe um endereço de email válido.")
	@NotEmpty(message = "Por favor, informe o email do cliente.")
	private String email;
	
	@Size(min = 8, message = "Por favor, informe uma senha com pelo menos 8 caracteres.")
	@NotEmpty(message = "Por favor, informe a senha do cliente.")
	private String senha;
	
}
