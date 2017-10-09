package com.matera.maladireta.domain.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.maladireta.domain.MalaDireta;
import com.matera.maladireta.domain.service.MalaDiretaService;

@RestController
@RequestMapping("/maladireta")
public class MalaDiretaResource {

	private final MalaDiretaService service;

	@Autowired
	public MalaDiretaResource(MalaDiretaService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	Integer process(@RequestBody List<MalaDireta> input) {
		return service.process(input);
	}
}
