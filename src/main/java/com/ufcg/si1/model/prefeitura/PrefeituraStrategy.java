package com.ufcg.si1.model.prefeitura;

import org.springframework.http.ResponseEntity;

public interface PrefeituraStrategy {
	
	static final int BOM = 2;
	static final int REGULAR = 1;
	static final int RUIM = 0;
	
	
	ResponseEntity<?> getSituacaoGeralQueixas(double numQueixasAbertas,
			int numTotalQueixas);

}
