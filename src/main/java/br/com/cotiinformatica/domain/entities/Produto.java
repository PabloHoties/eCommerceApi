package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_produto")
@Data
public class Produto {

	@Id
	@Column(name = "produto")
	private UUID id;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "preco", precision = 10, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@Column(name = "foto", nullable = false)
	private byte[] foto;
	
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itensPedido;
}
