package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.si1.model.Queixa;

@Repository
public interface QueixaRepository extends JpaRepository<Queixa, String>{

}
