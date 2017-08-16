package com.ufcg.si1.model;

public class QueixaAberta implements QueixaState {
	
	private static final int ABERTO = 1;

	public int getStatus(){
		return ABERTO;
	}

}
