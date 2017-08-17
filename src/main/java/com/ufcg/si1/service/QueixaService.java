package com.ufcg.si1.service;


import java.util.List;

import com.ufcg.si1.model.queixa.Queixa;
import com.ufcg.si1.model.queixa.QueixaAnimal;

public interface QueixaService {

	List<Queixa> findAllQueixas();


    void saveQueixa(Queixa queixa);
    
    void saveAnimalQueixa(QueixaAnimal queixa);


	Queixa findById(long id);

//	void updateQueixa(Queixa user);


	void deleteQueixaById(long id);

    int size();

//	Iterator<Queixa> getIterator();
    
    //Substituto do Iterator
    int numeroDeQueixasAbertas();


//	boolean isUserExist(Queixa user);
	
}
