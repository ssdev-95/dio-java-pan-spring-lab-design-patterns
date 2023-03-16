package org.saloma.patterns.repository;

import java.util.Optional;

import org.saloma.patterns.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
	  extends JpaRepository<Customer, Long> {
	Optional<Customer> findByCpf(String cpf);
}
