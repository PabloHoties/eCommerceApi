package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "tbl_pedido")
@Data
public class Pedido {

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datapedido", nullable = false)
	private Date dataPedido;
	
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;
}
