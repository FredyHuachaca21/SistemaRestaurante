package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.exception.PlatoNoEncontradoException;
import com.mixturaperuana.pe.model.Plato;

import java.util.List;

public interface PlatoRepository {
    List<Plato> findAll();
    Plato findById(int idPlato) throws PlatoNoEncontradoException;
    void save(Plato plato);
}
