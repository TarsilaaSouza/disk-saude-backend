package com.ufcg.si1.model;

public class QueixaFechada implements QueixaState {
	
	private static final int FECHADA = 3;

	public int getStatus(){
		return FECHADA;
	}

}
