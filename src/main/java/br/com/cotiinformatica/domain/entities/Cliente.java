package br.com.cotiinformatica.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_cliente")
@Data
public class Cliente {

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "cpf", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
}
