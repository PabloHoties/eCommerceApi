package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	// Consultar um cliente através do email
	@Query("select c from Cliente c where c.email = :pEmail")
	Cliente findByEmail(@Param("pEmail") String email);
	
	
	// Consultar um cliente através do cpf
	@Query("select c from Cliente c where c.cpf = :pCpf")
	Cliente findByCpf(@Param("pCpf") String cpf);
}
