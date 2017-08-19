package com.ufcg.si1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.model.saude.Especialidade;
import com.ufcg.si1.model.saude.PostoSaude;
import com.ufcg.si1.model.saude.UnidadeSaude;
import com.ufcg.si1.service.EspecialidadeService;
import com.ufcg.si1.service.EspecialidadeServiceImpl;
import com.ufcg.si1.service.UnidadeSaudeService;
import com.ufcg.si1.service.UnidadeSaudeServiceImpl;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;

import br.edu.ufcg.Hospital;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SaudeRestApiController {
	
	EspecialidadeService especialidadeService = new EspecialidadeServiceImpl();
    UnidadeSaudeService unidadeSaudeService = new UnidadeSaudeServiceImpl();
    
    
    @RequestMapping(value = "/especialidade/unidades", method = RequestMethod.GET)
    public ResponseEntity<?> consultaEspecialidadeporUnidadeSaude(@RequestBody int codigoUnidadeSaude) {

        Object us = null;
        try {
            us = unidadeSaudeService.procura(codigoUnidadeSaude);
        } catch (Rep e) {
            return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
        } catch (ObjetoInexistenteException e) {
            return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
        }
        //OLHAR
        if (us instanceof UnidadeSaude){
            UnidadeSaude us1 = (UnidadeSaude) us;
            return new ResponseEntity<>(us1.getEspecialidades(), HttpStatus.OK);
        }

        return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/unidade/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUnidades() {
        List<UnidadeSaude> unidades = unidadeSaudeService.getAll();
        if (unidades.isEmpty()) return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
        else{
            List<UnidadeSaude> unidadeSaudes = new ArrayList<>();
            for (Object  saude: unidades) {
                if(saude instanceof UnidadeSaude){
                    unidadeSaudes.add((UnidadeSaude) saude);
                }
            }
            return new ResponseEntity<>(unidadeSaudes, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/especialidade/", method = RequestMethod.POST)
    public ResponseEntity<Especialidade> incluirEspecialidade(@RequestBody Especialidade esp,
    		UriComponentsBuilder ucBuilder) {
       
    	try {
            especialidadeService.insere(esp);
        } catch (Rep e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ObjetoJaExistenteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    	return new ResponseEntity<Especialidade>(esp, HttpStatus.CREATED);
    }


    //how to save a subclass object?
    @RequestMapping(value = "/unidade/", method = RequestMethod.POST)
    public ResponseEntity<UnidadeSaude> incluirUnidadeSaude(@RequestBody UnidadeSaude unidadeSaude, UriComponentsBuilder ucBuilder){

  
       try {
		unidadeSaudeService.insere(unidadeSaude);
       } catch (ObjetoJaExistenteException e) {
    	   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        

      
        return new ResponseEntity<UnidadeSaude>(unidadeSaude, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/unidade/{idUnidade}/especialidade/{codigoEspecialidade}",
    		method = RequestMethod.PUT)
    public ResponseEntity<?> incluirEspecialidadeNaUnidade(
    		@PathVariable("idUnidade") int idUnidade, 
    		@PathVariable("codigoEspecialidade") int codigoEspecialidade) {
    	
    	UnidadeSaude unidade = unidadeSaudeService.findById(idUnidade);
    	Especialidade especialidade = especialidadeService.findByCodigo(codigoEspecialidade);
    	
    	if(unidade.equals(null) || especialidade.equals(null)) {
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	}
    	
    	unidade.adicionarEspecialidade(especialidade);
    	
    	return new ResponseEntity<Especialidade>(especialidade, HttpStatus.OK);
    }
    


    @RequestMapping(value = "/especialidade/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarEspecialidade(@PathVariable("id") long id) {

        Especialidade q = especialidadeService.findByCodigo(id);
        if (q == null) {
            return new ResponseEntity(new CustomErrorType("Especialidade with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Especialidade>(q, HttpStatus.OK);
    }

    @RequestMapping(value = "/unidade/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarUnidadeSaude(@PathVariable("id") long id) {

        UnidadeSaude us = unidadeSaudeService.findById(id);
        if (us.equals(null)) {
            return new ResponseEntity(new CustomErrorType("Unidade with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(us, HttpStatus.OK);
    }


    @RequestMapping(value = "/geral/medicos/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> calcularMediaMedicoPacienteDia(@PathVariable("id") long id) {

        Object unidade = unidadeSaudeService.findById(id);

        if(unidade == null){
            return new ResponseEntity<ObjWrapper<Double>>(HttpStatus.NOT_FOUND);
        }

        double c = 0.0;
        if (unidade instanceof PostoSaude)
            c = ((PostoSaude) unidade).getAtendentes()
                    / ((PostoSaude) unidade).getTaxaDiariaAtendimentos();
        else if (unidade instanceof Hospital){
            c = ((Hospital) unidade).getNumeroMedicos()
                    / ((Hospital) unidade).getNumeroPacientesDia();
        }
        return new ResponseEntity<ObjWrapper<Double>>(new ObjWrapper<Double>(new Double(c)), HttpStatus.OK);
    }
    
    @RequestMapping(value="/unidade/busca/{bairro}", method= RequestMethod.GET)
    public ResponseEntity<UnidadeSaude> consultarUnidadeSaudePorBairro(@PathVariable("bairro") String bairro){
        
    	UnidadeSaude unidadeDoBairro = unidadeSaudeService.findByBairro(bairro);
        
    	if (unidadeDoBairro.equals(null)) {
            return new ResponseEntity(new CustomErrorType("Unidade with bairro " + bairro
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UnidadeSaude>( unidadeDoBairro, HttpStatus.OK);
    }

}
