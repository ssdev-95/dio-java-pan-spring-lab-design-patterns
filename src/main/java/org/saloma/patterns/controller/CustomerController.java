package org.saloma.patterns.controller;

import org.saloma.patterns.model.Customer;
import org.saloma.patterns.model.dto.CustomerDTO;
import org.saloma.patterns.service.impl.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> register(
			@RequestBody CustomerDTO dto) {
		Customer customer = customerService.register(dto);
		return ResponseEntity.ok(customer);
	}

	@GetMapping
	public ResponseEntity<Iterable<Customer>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(
			@PathVariable("id") Long id) {
		try {
			Customer customer = customerService.findById(id);
			return ResponseEntity.ok(customer);
		} catch(NullPointerException e) {
			System.out.println("[EXCEPTION]" + e);
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/?cpf={cpf}")
	public ResponseEntity<Customer> findByCpf(
			@RequestParam("cpf") String cpf) {
		try {
			Customer customer = customerService.findByCpf(cpf);
			return ResponseEntity.ok(customer);
		} catch(NullPointerException e) {
			System.out.println("[EXCEPTION]" + e);
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateCustomer(
			@PathVariable("id") Long id,
			@RequestBody CustomerDTO dto) {
		try {
			customerService.update(id, dto);
			return ResponseEntity.ok(null);
		} catch(NullPointerException e) {
			System.out.println("[EXCEPTION]" + e);
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(
			@PathVariable("id") Long id) {
		try {
			customerService.delete(id);
			return ResponseEntity.ok(null);
		} catch(NullPointerException e) {
			System.out.println("[EXCEPTION]" + e);
			return ResponseEntity.notFound().build();
		}
	}
}
