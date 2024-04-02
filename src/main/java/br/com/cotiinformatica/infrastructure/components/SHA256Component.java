package br.com.cotiinformatica.infrastructure.components;

import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Component
public class SHA256Component {
	public String criptografarSHA256(String texto) {
		try {
			// Cria um objeto MessageDigest para o algoritmo SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			// Obtém o array de bytes da string a ser criptografada
			byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
			
			// Converte o array de bytes para representação hexadecimal
			StringBuilder hexString = new StringBuilder();
			
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
			
		} catch (NoSuchAlgorithmException e) {
			
			// Trata exceção se o algoritmo SHA-256 não estiver disponível
			e.printStackTrace();
			return null;
		}
	}
}
