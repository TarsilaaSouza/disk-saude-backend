package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ufcg.si1.repository.QueixaRepository;

@Entity
public class Queixa {

	@GeneratedValue
	@Id
	private int posInTable;
	private int id; 
	private String descricao;
	@OneToOne(cascade= CascadeType.ALL)
	private Pessoa pessoa;
	
	QueixaState queixaState;

	private String comentario = "";
	
	public Queixa(){
		id=0;
	}
	
	public Queixa(int id, String descricao, String comentario,
                  String nome, String email,
				  String rua, String uf, String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.comentario = comentario;
		this.pessoa = new Pessoa(nome, email, rua, uf, cidade);
	}

	public long getId() {
		return id;
	}

	public void setId() {
		this.id = this.hashCode();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getSituacao() {
		return queixaState.getStatus();
	}

	public void abrir() throws ObjetoInvalidoException {
		queixaState = new QueixaAberta();
	}
	
	public void fechar() {
		queixaState = new QueixaFechada();
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoa getSolicitante() {
		return pessoa;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.pessoa = solicitante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + posInTable;
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
		Queixa other = (Queixa) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (posInTable != other.posInTable)
			return false;
		return true;
	}
}
