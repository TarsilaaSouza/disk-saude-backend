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
  //  private Object[] vetor;
    private ArrayList<UnidadeSaude> unidades;

 //   private int indice;

    private int geraCodigo = 0; // para gerar codigos das queixas cadastradas

    public UnidadeSaudeServiceImpl() {
    //    vetor = new Object[100];
        unidades = new ArrayList<>();
      //  indice = 0;
 
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
    	
//        int i = 0;
//        
//        //se pecar vai pagar, se pecar vai morrer
//        while (i < indice) {
//        	
//            if (verificaUnidade(i)){
//            	
//                UnidadeSaude unidadeSaude = (UnidadeSaude) unidades.get(i);
//                
//                if(unidadeSaude.getCodigo() == codigo){
//                   
//                	return unidades.get(i);
//                }
//            }else if(verificaHospital(i)){
//            	
//                Hospital hospital = (Hospital) unidades.get(i);
//                
//                if(hospital.getCodigo() == codigo){
//                    return unidades.get(i);
//                }
//            }
//            i++;
//        }
//        throw new ObjetoInexistenteException("Não achou unidade");
    	
    }


//	private boolean verificaHospital(UnidadeSaude unidade) {
//		return unidade instanceof HospitalAdapter;
//	}
//
//
	private boolean verificaUnidade(UnidadeSaude unidade) {
		return unidade instanceof UnidadeSaude;
	}

    @Override
    public List<UnidadeSaude> getAll() {
    	
        return this.unidades;
    }
// daqui p baixo, so mexe quando fizer hospital
    @Override
    public void insere(UnidadeSaude us) throws Rep,
            ObjetoJaExistenteException {
    	
    	if(this.verificaUnidade(us)){
    		
    		if(!this.existe(us.getCodigo())){
    			
    			us.setCodigo(geraCodigo++);
    			this.unidades.add(us);
    		}
    			
    		throw new ObjetoJaExistenteException("Objeto jah existe no array");
    	}
    			
    	throw new Rep("Objeto nao eh uma unidade de saude");
    }

//        if (us == null) {
//        	throw new Rep("Erro!");
//        	
//        } else{
//        	
//        	if (us instanceof UnidadeSaude){
//        		
//        		((UnidadeSaude) us).setCodigo(++geraCodigo);
//        	}else {
//        		
//        		((Hospital) us).setCodigo(++geraCodigo);
//        	}
//        }
//DESNECESSARIO
//        if (indice == this.unidades.size()) {
//        	
//        	throw new Rep("Erro ao incluir no array");
//        }
//
//        if (us instanceof UnidadeSaude){
//        	UnidadeSaude unidadeSaude = (UnidadeSaude) us;
//        	if (this.existe(unidadeSaude.getCodigo())){
//        		throw new ObjetoJaExistenteException("Objeto jah existe no array");
//        	}
//        } else if (us instanceof Hospital){
//        	Hospital hospital = (Hospital) us;
//        	if (this.existe(hospital.getCodigo())){
//        		throw new ObjetoJaExistenteException("Objeto jah existe no array");
//        	}
//        }


       // this.vetor[indice] = us;
//        this.unidades.set(indice, us);
//        indice++;
 //   }
    	
    @Override
    public boolean existe(int codigo) {
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.getCodigo() == codigo){
    			
    			return true;
    		}
		}
    	
    	return false;
   
    }
//        int indiceAux = 0;
//        boolean existe = false;
//
//        for (int i = 0; i < indice; i++) {
//            if (this.unidades.get(i) instanceof UnidadeSaude){
//                UnidadeSaude unidadeSaude = (UnidadeSaude) unidades.get(i);
//                if (unidadeSaude.getCodigo() == codigo){
//                    indiceAux = i;
//                    existe = true;
//                    break;
//                }
//            }else if (this.unidades.get(i) instanceof Hospital){
//                Hospital hospital = (Hospital) unidades.get(i);
//                if (hospital.getCodigo() == codigo){
//                    indiceAux = i;
//                    existe = true;
//                    break;
//                }
//            }
//        }
//
//        return existe;
//    }

    public UnidadeSaude findById(long id) {
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.getCodigo() == (int)id){
    			
    			return unidadeSaude;
    		}
			
		}
    	return null;
    }
    	
//        for (Object esp: this.unidades) {
//            if (esp instanceof UnidadeSaude){
//                UnidadeSaude unidadeSaude = (UnidadeSaude) esp;
//                if (unidadeSaude != null && unidadeSaude.getCodigo() == id){
//                    return unidadeSaude;
//                }
//            }else if (esp instanceof Hospital){
//                Hospital hospital = (Hospital) esp;
//                if (hospital != null && hospital.getCodigo() == id){
//                    return hospital;
//                }
//            }
//        }
//        return null;
//    }

    @Override
    public UnidadeSaude findByBairro(String bairro) {
    	
    	for (UnidadeSaude unidadeSaude : unidades) {
    		
    		if(unidadeSaude.pegaDescricao().equals(bairro)){
    			
    			return unidadeSaude;
    		}
		}
    	return null;
    }
//        for (Object esp: this.unidades) {
//            if (esp instanceof UnidadeSaude){;
//                UnidadeSaude u = (UnidadeSaude) esp;
//                if (u.pegaDescricao().equals(bairro)){
//                    return esp;
//                }
//            } else if (esp instanceof Hospital){
//                Hospital h = (Hospital) esp;
//                if (h.getDescricao().equals(bairro)){
//                    return esp;
//                }
//            }
//        }
//        return null;
//    }

}
