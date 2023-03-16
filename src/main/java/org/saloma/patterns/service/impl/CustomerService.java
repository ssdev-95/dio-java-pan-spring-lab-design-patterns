package org.saloma.patterns.service.impl;

import java.util.Optional;

import org.aspectj.weaver.ast.CallExpr;
import org.saloma.patterns.model.Address;
import org.saloma.patterns.model.Customer;
import org.saloma.patterns.model.dto.CustomerDTO;
import org.saloma.patterns.repository.AddressRepository;
import org.saloma.patterns.repository.CustomerRepository;
import org.saloma.patterns.service.ICustomerService;
import org.saloma.patterns.service.IViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
	private final String ERR_MSG = "Customer not found for %1$s:%2$s";
	@Autowired
	private CustomerRepository cRepository;
	@Autowired
	private AddressRepository aRepository;
	@Autowired
	private IViaCepService viaCepService;

	@Override
	public Customer register(CustomerDTO dto) {
		Address address = getAddressOrSave(dto.getCep());

		Customer customer = new Customer(
				null, dto.getName(), dto.getCpf(), address);
		return cRepository.save(customer);
	}

	@Override
	public Iterable<Customer> findAll() {
		return cRepository.findAll();
	}

	@Override
	public Customer findById(Long id) throws NullPointerException {
		Optional<Customer> customer = cRepository.findById(id);
		
		if(!customer.isPresent()) throw new NullPointerException(
				String.format(ERR_MSG, "id", id));
		
		return customer.get();
	}

	@Override
	public Customer findByCpf(String cpf)
		  throws NullPointerException {
		Optional<Customer> customer = cRepository.findByCpf(cpf);

		if(!customer.isPresent()) throw new NullPointerException(
				String.format(ERR_MSG, "cpf", cpf));

		return customer.get();
	}

	@Override
	public void update(Long id, CustomerDTO dto)
		  throws NullPointerException {
		Optional<Customer> customerExists = cRepository.findById(id);

		if(!customerExists.isPresent())
			throw new NullPointerException(
					String.format(ERR_MSG, "id", id));

		Address address = getAddressOrSave(dto.getCep());

		Customer customer = customerExists.get();
		customer.setName(dto.getName());
		customer.setAddress(address);
		cRepository.save(customer);
	}

	@Override
	public void delete(Long id) throws NullPointerException {
		Optional<Customer> customer = cRepository.findById(id);

		if(!customer.isPresent()) throw new NullPointerException(
				String.format(ERR_MSG, "id", id));

		cRepository.deleteById(id);
	}

	private Address getAddressOrSave(String cep) {
		return aRepository.findByCep(cep)
			.orElseGet(() -> {
				return viaCepService.consulteCep(cep);
			});
	}
}
