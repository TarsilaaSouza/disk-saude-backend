package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.repository.QueixaRepository;


@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {

//    private static final AtomicLong counter = new AtomicLong(); -- ID DEFINIDO PELO HASHCODE
    
    @Autowired
	QueixaRepository queixaRepository;
    
   

//    private static List<Queixa> queixas;
//
//    static {
//        queixas = populateDummyQueixas();
//    }
    
    //TEVE Q SAIR PRA PERSISTENCIA FUNCIONAR DE BOAS
//    private List<Queixa> queixas = this.populateDummyQueixas();
//
//    private static List<Queixa> populateDummyQueixas() {
//        List<Queixa> queixas = new ArrayList<Queixa>();
//
//        queixas.add(new Queixa(counter.incrementAndGet(), "Passei mal com uma coxinha",
//                Queixa.FECHADA, "", "Jose Silva",
//                "jose@gmail.com", "rua dos tolos", "PE", "Recife"));
//
//
//        queixas.add(new Queixa(counter.incrementAndGet(),
//                "Bacalhau estragado, passamos mal!", Queixa.FECHADA, "",
//                "Ailton Sousa", "ailton@gmail.com", "rua dos bobos", "PB",
//                "Joao Pessoa"));
//
//        queixas.add(new Queixa(counter.incrementAndGet(), "Nossa rua estah muito suja", Queixa.FECHADA, "",
//                "Jose Silva", "jose@gmail.com", "rua dos tolos", "PE", "Recife"));
//
//
//        queixas.add(new Queixa(counter.incrementAndGet(), "iluminacao horrivel, muitos assaltos", Queixa.FECHADA, "",
//                "Ailton Sousa", "ailton@gmail.com", "rua dos bobos", "PB",
//                "Joao Pessoa"));
//
//        return queixas;
//    }

    public List<Queixa> findAllQueixas() {
        return queixaRepository.findAll();
    }

    public void saveQueixa(Queixa queixa) {
        queixa.setId(); // ID DEFINIDO PELO HASHCODE AGORA
//        queixas.add(queixa);
        queixaRepository.save(queixa);
    }
    
    //recebe uma queixa alterada, pesquisa pela queixa original e a substitui 
    // pela alterada
//    public void updateQueixa(Queixa queixa) {
//        int index = queixas.indexOf(queixa);
//        queixas.set(index, queixa); 
//    }
//
//    
    
    //Nao esta funfando - mas provavelmente o erro é no controller
    public void updateQueixa(Queixa queixa) {
    	
    	Queixa queixaOriginal = this.findById(queixa.getId());
    	
    	queixaOriginal.setComentario(queixa.getComentario());
    	queixaOriginal.setDescricao(queixa.getDescricao());
    	queixaOriginal.setSolicitante(queixa.getSolicitante());
       
    }

    
    public void deleteQueixaById(long id) {
    	//Possivel badsmell
//        for (Iterator<Queixa> iterator = queixas.iterator(); iterator.hasNext(); ) {
//            Queixa q = iterator.next();
//            if (q.getId() == id) {
//                iterator.remove();
//            }
//        }
    	Queixa queixa = this.findById(id);
    	this.queixaRepository.delete(queixa);
    }
   
    // kkkkkkk mds
    //este metodo nunca eh chamado, mas se precisar estah aqui
    public int size() {
        return queixaRepository.findAll().size();
    }


    public void deleteAllUsers() {
        queixaRepository.deleteAll();
    }

    public Queixa findById(long id) {
        
    	for(Queixa queixa : queixaRepository.findAll()) {
    		if(queixa.getId() == id) {
    			return queixa;
    		}
    	}
    	
    	return null;
    }
    
    public int numeroDeQueixasAbertas( ) {
    	int contador = 0;
    	
    	for(Queixa queixa: queixaRepository.findAll()) {
    		if(queixa.getSituacao() == Queixa.ABERTA) {
    			contador++;
    		}
    	}
    	
    	return contador;
    }



}