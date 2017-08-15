package com.ufcg.si1.model;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GeneratorType;

//No mundo em que pessoas eram filhas dos endereços

@Entity
public class Pessoa{

	@GeneratedValue
	@Id
	int id = this.hashCode();
	
	private String nome;
	
	private String email;
	
	@OneToOne
	private Endereco endereco;
	
	//NIGUEM MEXE, POIS NAO SABEMOS COMO ISSO ESTA FUNCIONANDO
	public Pessoa(){
		super();
	}


	public Pessoa(String nome, String email, String rua, String uf,
			String cidade) {
//		super(rua, uf, cidade);	
		this.nome = nome;
		this.email = email;
		this.endereco = new Endereco(rua, uf, cidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
