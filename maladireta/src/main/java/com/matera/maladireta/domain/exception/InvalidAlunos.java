package com.matera.maladireta.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Alunos not found")
public class InvalidAlunos extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
