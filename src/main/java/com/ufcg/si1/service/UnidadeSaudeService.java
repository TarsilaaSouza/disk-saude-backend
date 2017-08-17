package com.ufcg.si1.service;

import java.util.List;

import com.ufcg.si1.model.saude.UnidadeSaude;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;


public interface UnidadeSaudeService {
    UnidadeSaude procura(int codigo) throws Rep,
            ObjetoInexistenteException;

    List<UnidadeSaude> getAll();

    void insere(UnidadeSaude us)throws Rep,
            ObjetoJaExistenteException;

    boolean existe(int codigo);

    UnidadeSaude findById(long id);

    UnidadeSaude findByBairro(String bairro);
}
