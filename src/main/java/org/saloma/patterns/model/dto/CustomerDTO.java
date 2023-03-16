package org.saloma.patterns.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	@NotNull(message = "Empty fields aren't allowed: name")
	@Size(min = 5, max = 30, message = "Name size must be between 5 and 30 characters.")
	private String name;
	@NotNull(message = "Empty fields aren't allowed: name")
	private String cpf;
	@NotNull(message = "Empty fields aren't allowed: name")
	private String cep;
}
