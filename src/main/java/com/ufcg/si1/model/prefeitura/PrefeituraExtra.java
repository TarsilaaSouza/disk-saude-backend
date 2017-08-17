package com.ufcg.si1.model.prefeitura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.si1.util.ObjWrapper;

public class PrefeituraExtra implements PrefeituraStrategy{

	@Override
	public ResponseEntity<?> getSituacaoGeralQueixas(double numQueixasAbertas,
			int numTotalQueixas) {
		
		if ( numQueixasAbertas / numTotalQueixas > 0.1) {
           
			return generateResponseEntity(RUIM);
       
		}else if (numQueixasAbertas / numTotalQueixas > 0.05) {
         
			return generateResponseEntity(REGULAR);
        
		}else {
			
			return generateResponseEntity(BOM);
        }

	}
	
	private ResponseEntity<?> generateResponseEntity (int state) {
		return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(state)
				, HttpStatus.OK);
	}
}
