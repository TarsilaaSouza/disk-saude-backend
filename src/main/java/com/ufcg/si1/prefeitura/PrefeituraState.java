package com.ufcg.si1.prefeitura;

import org.springframework.http.ResponseEntity;

public interface PrefeituraState {
	
	static final int BOM = 2;
	static final int REGULAR = 1;
	static final int RUIM = 0;
	
	
	ResponseEntity<?> getSituacaoGeralQueixas(double numQueixasAbertas,
			int numTotalQueixas);

}
