package com.ufcg.si1.prefeitura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.si1.util.ObjWrapper;

public class PrefeituraNormal implements PrefeituraState {

	@Override
	public ResponseEntity<?> getSituacaoGeralQueixas(double numQueixasAbertas,
			int numTotalQueixas) {
		
		if (numQueixasAbertas / numTotalQueixas > 0.2) {
           
			return generateResponseEntity(RUIM);
       
		}else if (numQueixasAbertas / numTotalQueixas > 0.1) {
         
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
