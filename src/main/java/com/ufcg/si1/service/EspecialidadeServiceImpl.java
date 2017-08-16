package com.ufcg.si1.service;

import com.ufcg.si1.model.Especialidade;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("especialidadeService")
public class EspecialidadeServiceImpl implements EspecialidadeService {

	private List<Especialidade> especialidades;
    private int indice;


    public EspecialidadeServiceImpl() {
    	
    	
    	this.especialidades = new ArrayList<>();
        this.indice = 0;
    }

    @Override
    public Especialidade procura(int codigo) throws Rep,
            ObjetoInexistenteException {

    	for (Especialidade especialidade : especialidades) {
			
    		if(especialidade.getCodigo() == codigo){
    			
    			return especialidade;
    		}
		}

        throw new ObjetoInexistenteException("Erro Especialidade");
    }

    @Override
    public List<Especialidade> getListaEspecialidade()
            throws Rep, ObjetoInexistenteException {
        //return Arrays.asList(vetor);
    	return this.especialidades;
    }

    @Override
    public int size() {
        //return this.indice;
    	return this.especialidades.size();
    }

    @Override
    public Especialidade getElemento(int posicao) {
        /*if (posicao < indice)
            return this.vetor[posicao];
        else
            return null;*/
    	if(posicao < this.size()){
    		
    		return this.especialidades.get(posicao);
    	}
    	return null;
    }

    @Override
    public void insere(Especialidade especialidade) throws Rep,
            ObjetoJaExistenteException {
    	
    	int novoCodigo = this.gerarCodigo();
    	
       // especialidade.setCodigo(++geraCodigo);
    	
    	especialidade.setCodigo(novoCodigo);

       /* if (indice == this.vetor.length) {
            throw new Rep("Erro ao incluir no array");
        }
        
        por que ter uma restricao de especialidades?
*/
    	
        if (this.findById(especialidade.getCodigo()) != null) {
        	
            throw new ObjetoJaExistenteException("Objeto jah existe no array");
        }

        this.especialidades.add(especialidade);
//        this.vetor[indice] = especialidade;
//        indice++;
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

    public Especialidade findById(long id) {
  
    	for (Especialidade especialidade : especialidades) {
			
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
    private int gerarCodigo(){
    	
    	int ultimoIndice = this.size() - 1;
    	Especialidade ultimaEspecialidade = this.especialidades.get(ultimoIndice);
    	int novoCodigo = ultimaEspecialidade.getCodigo() + 1;
    	
    	return novoCodigo;
    }


}