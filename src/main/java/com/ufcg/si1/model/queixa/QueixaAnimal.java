package com.ufcg.si1.model.queixa;

import com.ufcg.si1.model.pessoa.Animal;

public class QueixaAnimal extends Queixa{
	
	private Animal animal;
	
	public QueixaAnimal(int id, String descricao, String comentario,
            String nome, String email,
			  String rua, String uf, String cidade, String tipo,
			  String ruaAnimal, String ufAnimal, String cidadeAnimal) {
		
		super(id, descricao, comentario, nome, email, rua, uf, cidade);
		this.animal = new Animal(tipo, ruaAnimal, ufAnimal, cidadeAnimal);
		
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
}
