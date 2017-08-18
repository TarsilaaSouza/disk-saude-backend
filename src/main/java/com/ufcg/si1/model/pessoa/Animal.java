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
	
	public Animal() {
		super();
	}
	
	public Animal(String tipo, String rua, String uf, String cidade){
		
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
