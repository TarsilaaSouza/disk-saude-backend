package com.ufcg.si1.model;

public enum QueixaStatus {
	
	ABERTA(1),
	EM_ANDAMENTO(2),
	FECHADA(3);
	
	public int vallue;
	
	QueixaStatus(int vallue) {
		this.vallue = vallue;
	}

}
