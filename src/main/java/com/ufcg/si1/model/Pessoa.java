package com.ufcg.si1.model;


//No mundo em que pessoas eram filhas dos endereços
public class Pessoa{
	
	private String nome;

	private String email;
	
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

}
