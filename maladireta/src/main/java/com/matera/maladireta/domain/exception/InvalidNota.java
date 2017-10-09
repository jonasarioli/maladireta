package com.matera.maladireta.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nota not found or invalid")
public class InvalidNota extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String cpf;

    public String getCpf() {
		return cpf;
	}

	public InvalidNota(String cpf) {
        this.cpf = cpf;
    }

}
