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

	public int situacao; 
	
	public static final int ABERTA = 1;
	public static final int EM_ANDAMENTO = 2;
	public static final int FECHADA = 3;

	private String comentario = "";
	
	public Queixa(){
		id=0;
	}
	
	//BAD SMELL - LONGA LISTA DE PARAMETROS
	public Queixa(int id, String descricao, int situacao, String comentario,
                  String nome, String email,
				  String rua, String uf, String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.situacao = situacao;
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
		return situacao;
	}

	public void abrir() throws ObjetoInvalidoException {
		if (this.situacao != Queixa.EM_ANDAMENTO){
			this.situacao = Queixa.ABERTA;
		
		}else
			throw new ObjetoInvalidoException("Status inválido");
	}
	
	public void fechar(String coment) throws ObjetoInvalidoException {
		
		if (this.situacao == Queixa.EM_ANDAMENTO
				|| this.situacao == Queixa.ABERTA) {
		
			this.situacao = Queixa.FECHADA;
			this.comentario = coment;
		
		} else
			throw new ObjetoInvalidoException("Status Inválido");
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
		result = prime * result + situacao;
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
		if (situacao != other.situacao)
			return false;
		return true;
	}

}
