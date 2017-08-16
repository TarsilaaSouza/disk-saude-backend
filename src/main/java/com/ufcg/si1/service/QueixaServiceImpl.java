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

    
    private static final int ABERTA = 1;
	@Autowired
	QueixaRepository queixaRepository;
    
   

    public List<Queixa> findAllQueixas() {
        return queixaRepository.findAll();
    }

    public void saveQueixa(Queixa queixa) {
        queixa.setId();
        queixaRepository.save(queixa);
    }
    
    
    public void updateQueixa(Queixa queixa) {
    	
    	Queixa queixaOriginal = this.findById(queixa.getId());
    	
    	queixaOriginal.setComentario(queixa.getComentario());
    	queixaOriginal.setDescricao(queixa.getDescricao());
    	queixaOriginal.setSolicitante(queixa.getSolicitante());
       
    }

    
    public void deleteQueixaById(long id) {
    	Queixa queixa = this.findById(id);
    	this.queixaRepository.delete(queixa);
    }
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
    		if(queixa.getSituacao() == ABERTA) {
    			contador++;
    		}
    	}
    	
    	return contador;
    }



}