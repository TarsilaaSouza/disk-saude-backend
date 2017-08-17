package com.ufcg.si1.model.queixa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.ufcg.si1.model.pessoa.Animal;

@Entity
public class QueixaAnimal extends Queixa{
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Animal animal;
	
	public QueixaAnimal() {
		super();
	}
	
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
