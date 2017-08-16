package com.ufcg.si1.model;

public class QueixaEmAndamento implements QueixaState {
	
	private static final int EM_ANDAMENTO = 2;

	public int getStatus(){
		return EM_ANDAMENTO;
	}

}
