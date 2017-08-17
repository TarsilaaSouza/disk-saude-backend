package com.ufcg.si1.model.pessoa;

public class Animal {
	
	private String tipo;
	private Endereco endereco;
	
	public Animal(String tipo, String rua, String uf, String cidade){
		
		this.tipo = tipo;
		this.endereco = new Endereco(rua, uf, cidade);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
