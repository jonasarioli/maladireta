package com.matera.maladireta.domain;

public class MalaDireta {

	private String nome;
	private String endereco;
	private String cep;
	private String mensagem;
	
	public MalaDireta() {
		
	}
	
	public MalaDireta(Aluno aluno) {
		this.nome = aluno.getNome();
		this.endereco = aluno.getEndereco();
		this.cep = aluno.getCep();
		this.mensagem = "Texto referente a mensagem para o aluno";
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
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
