package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufcg.si1.model.saude.Especialidade;
import com.ufcg.si1.model.saude.UnidadeSaude;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {
 
    private ArrayList<UnidadeSaude> unidades;



    private int geraCodigo = 0; 

    public UnidadeSaudeServiceImpl() {
 
        unidades = new ArrayList<>();
    
 
    }


    @Override
    public UnidadeSaude procura(int codigo) throws Rep,
            ObjetoInexistenteException {
    	
    	for (UnidadeSaude unidade : unidades) {
    		
    		if(unidade.getCodigo() == codigo){
    			
    			return unidade;
    		}
    	}
    	throw new ObjetoInexistenteException("Nao achou unidade");
    	
    	
    }



    @Override
    public List<UnidadeSaude> getAll() {
    	
        return this.unidades;
    }

    @Override
    public void insere(UnidadeSaude unidadeSaude) throws ObjetoJaExistenteException {
    	
    	if(this.unidades.contains(unidadeSaude)) {
    		throw new ObjetoJaExistenteException("Unidade ja existe");
    	}
    		
    	unidadeSaude.setCodigo();
    	this.unidades.add(unidadeSaude);
    }
   	
    @Override
    public boolean existe(int codigo) {
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.getCodigo() == codigo){
    			
    			return true;
    		}
		}
    	
    	return false;
   
    }

    public UnidadeSaude findById(long id) {
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.getCodigo() == (int)id){
    			
    			return unidadeSaude;
    		}
			
		}
    	return null;
    }
    	

    @Override
    public UnidadeSaude findByBairro(String bairro) {
    	
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		if(unidadeSaude.getBairro().equals(bairro)) {
    			return unidadeSaude;
    		}
		}
    	
    	return null;
    }
    
    public List<UnidadeSaude> findByEspecialidade(Especialidade especialidade){
    	
    	List<UnidadeSaude> unidadesEncontradas = new ArrayList<>();
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.findEspecialidade(especialidade)){
    			
    			unidadesEncontradas.add(unidadeSaude);
    		}
		}
    	
    	return unidadesEncontradas;
    }
}
