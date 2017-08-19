package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.model.prefeitura.PrefeituraCaos;
import com.ufcg.si1.model.prefeitura.PrefeituraExtra;
import com.ufcg.si1.model.prefeitura.PrefeituraNormal;
import com.ufcg.si1.model.prefeitura.PrefeituraStrategy;
import com.ufcg.si1.model.queixa.Queixa;
import com.ufcg.si1.model.queixa.QueixaAnimal;
import com.ufcg.si1.model.queixa.QueixaStatus;
import com.ufcg.si1.service.QueixaService;
import com.ufcg.si1.service.QueixaServiceImpl;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QueixaRestApiController {
	
	@Autowired
    QueixaService queixaService = new QueixaServiceImpl();
	
	public static PrefeituraStrategy prefeituraStrategy = new PrefeituraNormal();
	
	
	@RequestMapping(value = "/queixa/", method = RequestMethod.GET)
    public ResponseEntity<List<Queixa>> listAllQueixas() {
        List<Queixa> queixas = queixaService.findAllQueixas();

        if (queixas.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK);
    }


    @RequestMapping(value = "/queixa/", method = RequestMethod.POST)
    public ResponseEntity<?> abrirQueixa(@RequestBody Queixa queixa, UriComponentsBuilder ucBuilder) {

        try {
            queixa.abrir();
        } catch (ObjetoInvalidoException e) {
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
        queixaService.saveQueixa(queixa);

        return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/queixa/animal/", method = RequestMethod.POST)
    public ResponseEntity<?> abrirQueixaAnimal(@RequestBody QueixaAnimal queixaAnimal,
    		UriComponentsBuilder ucBuilder) {

        try {
            queixaAnimal.abrir();
        } catch (ObjetoInvalidoException e) {
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
        queixaService.saveAnimalQueixa(queixaAnimal);
    	
   
        return new ResponseEntity<QueixaAnimal>(queixaAnimal, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarQueixa(@PathVariable("id") long id) {

        Queixa queixa = queixaService.findById(id);
        if (queixa == null) {
            return new ResponseEntity(new CustomErrorType("Queixa with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Queixa>(queixa, HttpStatus.OK);
    }


    @RequestMapping(value = "/queixa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQueixa(@PathVariable("id") long id, @RequestBody Queixa queixa) {

        Queixa currentQueixa = queixaService.findById(id);

        if (currentQueixa == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Queixa with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }else {
        	currentQueixa.setDescricao(queixa.getDescricao());
            currentQueixa.setComentario(queixa.getComentario());
            
            queixaService.saveQueixa(currentQueixa);
            
            return new ResponseEntity<Queixa>(currentQueixa, HttpStatus.OK);
        }
    }
   

    @RequestMapping(value = "/queixa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQueixa(@PathVariable("id") long id) {

        Queixa user = queixaService.findById(id);
        if (user == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Queixa with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        
        queixaService.deleteQueixaById(id);
        return new ResponseEntity<Queixa>(HttpStatus.OK);
    }


    @RequestMapping(value = "/queixa/fechamento", method = RequestMethod.POST)
    public ResponseEntity<?> fecharQueixa(@RequestBody Queixa queixaAFechar) {
        queixaAFechar.fechar();
        queixaService.saveQueixa(queixaAFechar);
        return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/queixa/modificacao/{comentario}/{status}/{id}",
    		method = RequestMethod.PUT)
    public ResponseEntity<?> modificarQueixa(
    		@PathVariable("comentario") String comentario,
    		@PathVariable("status") int status,
    		@PathVariable("id") int id) {
        
    	Queixa queixaAtual = queixaService.findById(id);
    	
    	if (queixaAtual.equals(null)){
    		return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
    	
    	}else if (status == QueixaStatus.EM_ANDAMENTO.vallue){
      		queixaAtual.setSituacao(QueixaStatus.EM_ANDAMENTO);
    	
    	}else if (status == QueixaStatus.FECHADA.vallue){
    		queixaAtual.setSituacao(QueixaStatus.FECHADA);
    	}
    	
		queixaAtual.setComentario(comentario);
		queixaService.saveQueixa(queixaAtual);
        return new ResponseEntity<Queixa>(queixaAtual, HttpStatus.OK);
    }
    
    //Reparar no relatorio
    @RequestMapping(value = "/geral/situacao", method = RequestMethod.GET)
    public ResponseEntity<?> getSituacaoGeralQueixas() {
    	
    	
    	return prefeituraStrategy.getSituacaoGeralQueixas(numeroQueixasAbertas(),
    			queixaService.size());

    }
    
    private double numeroQueixasAbertas() {
        return queixaService.numeroDeQueixasAbertas();
    }
    
    @RequestMapping(value = "/prefeitura/situacao/{situacao}", method = RequestMethod.PUT)
    public ResponseEntity<?> setSituacaoPrefeitura(@PathVariable("situacao") int situacao) {
    	
    	if(situacao == 0) {
    	
    		this.prefeituraStrategy = new PrefeituraNormal();
    		return new ResponseEntity<List>(HttpStatus.ACCEPTED);
    	
    	}else if (situacao == 1) {
    		
    		this.prefeituraStrategy = new PrefeituraExtra();
    		return new ResponseEntity<List>(HttpStatus.ACCEPTED);
    	
    	}else if(situacao == 2) {
    		this.prefeituraStrategy = new PrefeituraCaos();
    		return new ResponseEntity<List>(HttpStatus.ACCEPTED);
    	}
    	
    	else {
    		return new ResponseEntity<List>(HttpStatus.NOT_ACCEPTABLE);
    	}
    }


}
