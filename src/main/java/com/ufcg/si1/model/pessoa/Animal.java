package com.ufcg.si1.model.pessoa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Animal {
	
	@GeneratedValue
	@Id
	int idInTable;
	
	private String tipo;
	@OneToOne
	private Endereco local;
	
	public Animal() {
		super();
	}
	
	public Animal(String tipo, String rua, String uf, String cidade){
		
		this.tipo = tipo;
		this.local = new Endereco(rua, uf, cidade);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return local;
	}

	public void setEndereco(Endereco endereco) {
		this.local = endereco;
	}
}
