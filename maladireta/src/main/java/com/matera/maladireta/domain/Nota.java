package com.matera.maladireta.domain;

import java.util.List;

public class Nota {

	private String cpf;
	private List<Disciplina> notas;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Disciplina> getNotas() {
		return notas;
	}
	public void setNotas(List<Disciplina> notas) {
		this.notas = notas;
	}
} 
