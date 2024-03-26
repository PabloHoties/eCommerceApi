package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}
