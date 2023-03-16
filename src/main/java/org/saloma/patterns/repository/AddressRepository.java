package org.saloma.patterns.repository;

import java.util.Optional;

import org.saloma.patterns.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository
  	extends JpaRepository<Address, Long> {
	Optional<Address> findByCep(String cep);
}
