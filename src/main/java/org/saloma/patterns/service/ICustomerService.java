package org.saloma.patterns.service;

import org.saloma.patterns.model.Customer;
import org.saloma.patterns.model.dto.CustomerDTO;

public interface ICustomerService {
	Customer register(CustomerDTO dto);
	Iterable<Customer> findAll();
	Customer findById(Long id);
	Customer findByCpf(String cpf);
	void update(Long id, CustomerDTO dto);
	void delete(Long id);
}
