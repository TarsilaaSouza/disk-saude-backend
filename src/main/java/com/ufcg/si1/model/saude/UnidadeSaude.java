package com.ufcg.si1.model.saude;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;


public class UnidadeSaude {
    private int codigo;

    private String descricao;
    
    private String bairro;

    private List<Especialidade> especialidades = new ArrayList<>();
    
    private List numeroQueixas = new ArrayList();
    
    public UnidadeSaude() {
    }

    public UnidadeSaude(String descricao) {
        this.codigo = 0; 
        this.descricao = descricao;
    }

    public void addQueixaProxima(long id) {
       
    	this.numeroQueixas.add(id);
    }

    public String pegaDescricao() {
        return this.descricao;
    }

    public void mudaDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public void adicionarEspecialidade(Especialidade esp) {
        this.especialidades.add(esp);
    }
    
    public boolean findEspecialidade(Especialidade esp){
    	
    	for (Especialidade especialidade : especialidades) {
			
    		if(especialidade.getCodigo() == esp.getCodigo()){
    			
    			return true;
    		}
		}
    	return false;
    	
    }
    
    
    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo() {
        this.codigo = this.hashCode();
    }
    
    

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		UnidadeSaude other = (UnidadeSaude) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
}