package com.ufcg.si1.model.saude;
 
 
 public class Especialidade {
 
     private int codigo;
 
     private String descricao;
 
     public Especialidade(String descricao) {
         this.codigo = 0; 
         this.descricao = descricao;
     }
 
     public Especialidade(){
 
     }
 
     public String getDescricao() {
         return this.descricao;
     }
 
     public void setDescricao(String descricao) {
         this.descricao = descricao;
     }
 
     public int getCodigo() {
         return this.codigo;
     }
 
     public void setCodigo() {
         if(this.codigo == 0) {
        	 this.codigo = this.hashCode();
         }
     }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		
		if(result < 0) {
			return result * -1;
		}
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
     
     
     
     
 }