package br.com.cotiinformatica.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_itempedido")
@Data
public class ItemPedido {

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
}
