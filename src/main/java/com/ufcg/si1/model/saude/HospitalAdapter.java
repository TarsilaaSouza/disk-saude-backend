package com.ufcg.si1.model.saude;

import br.edu.ufcg.Hospital;

public class HospitalAdapter extends UnidadeSaude {

	private Hospital hospital;
	
	public HospitalAdapter(String descricao, int medicos, int num) {
		super(descricao);
		this.hospital = new Hospital(descricao, medicos, num);
	}
	
	public int getCodigo(){
		return this.hospital.getCodigo();
	}
	
	public void setCodigo (int codigo){
		this.hospital.setCodigo(codigo);
	}
	
	public String getDescricao(){
		return this.hospital.getDescricao();
	}
	
	public int getContador(){
		return this.hospital.getContador();
	}
	
	public int getNumeroMedicos(){
		return this.hospital.getNumeroMedicos();
	}
	
	public double getNumeroPacientePorDia(){
		return this.hospital.getNumeroPacientesDia();
	}
}