package com.matera.maladireta.domain;

import java.io.Serializable;

public class Aluno implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String documento;
	private String nome;
	private String endereco;
	private String cep;
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
