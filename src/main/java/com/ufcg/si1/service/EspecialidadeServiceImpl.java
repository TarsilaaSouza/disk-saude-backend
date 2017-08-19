package com.ufcg.si1.service;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.saude.Especialidade;
import com.ufcg.si1.repository.EspecialidadeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("especialidadeService")
public class EspecialidadeServiceImpl implements EspecialidadeService {


	@Autowired
	EspecialidadeRepository especialidadeRepository;
	


    public EspecialidadeServiceImpl() {    	
    }

    @Override
    public Especialidade procura(int codigo) throws Rep,
            ObjetoInexistenteException {

    	for (Especialidade especialidade : especialidadeRepository.findAll()) {
			
    		if(especialidade.getCodigo() == codigo){
    			
    			return especialidade;
    		}
		}

        throw new ObjetoInexistenteException("Erro Especialidade");
    }

    @Override
    public List<Especialidade> getListaEspecialidade()
            throws Rep, ObjetoInexistenteException {
        
    	if(this.especialidadeRepository.findAll().isEmpty()) {
        	throw new ObjetoInexistenteException("Nenhuma especialidade foi adicionada");
        }
        
        return this.especialidadeRepository.findAll();
    }

    @Override
    public int size() {
        //return this.indice;
    	return this.especialidadeRepository.findAll().size();
    }

    @Override
    public Especialidade getElemento(int posicao) {
        /*if (posicao < indice)
            return this.vetor[posicao];
        else
            return null;*/
    	if(posicao < this.size()){
    		
    		return this.especialidadeRepository.findAll().get(posicao);
    	}
    	return null;
    }

    @Override
    public void insere(Especialidade especialidade) throws ObjetoJaExistenteException{
    	especialidade.setCodigo();
    	
    	if(this.especialidadeRepository.findAll().contains(especialidade)) {
    		throw new ObjetoJaExistenteException("Especialidade já existente");
    	}
    	
        this.especialidadeRepository.save(especialidade);
    }

    @Override
 /*   public boolean existe(int codigo) {
    	
    	for (Especialidade especialidade : especialidades) {
			
    		if(especialidade.getCodigo() == codigo){
    			
    			return true;
    		}
		}
    	
    	return false;
    	
    	-- CODIGO ORIGINAL -- ELEONORA --
    	
        int indiceAux = 0;
        boolean existe = false;
        for (int i = 0; i < indice; i++) {
            if (this.vetor[i].getCodigo() == codigo) {
                indiceAux = i;
                existe = true;
                break;
            }
        }
        return existe;
    	
    	
    }*/

    public Especialidade findByCodigo(long id) {
  
    	for (Especialidade especialidade : this.especialidadeRepository.findAll()) {
			
    		if(especialidade.getCodigo() == id){
    			
    			return especialidade;
    		}
		}
    	
    	return null;    	
    	
 /*       for (Especialidade esp: vetor) {
            if (esp.getCodigo() == id) {
                return esp;
            }
        }
        return null; */
    }
}