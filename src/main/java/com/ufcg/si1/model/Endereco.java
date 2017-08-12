package com.ufcg.si1.model;

public class Endereco {

	private String rua;

	private String uf;

	private String cidade;

	public Endereco(String rua, String uf, String cidade) {
		this.rua = rua;
		this.uf = uf;
		this.cidade = cidade;
	}

	public String getRua() {
		return this.rua;
	}
	
	public void setRua(String novaRua){
		this.rua = novaRua;
		
	}

	public String getUf() {
		return this.uf;
	}
	
	public void setUf(String novaUf){
		this.uf = novaUf;
	}

	public String getCidade() {
		return this.cidade;
	}
	
	public void setCidade(String novaCidade){
		this.cidade = novaCidade;
	}
}
