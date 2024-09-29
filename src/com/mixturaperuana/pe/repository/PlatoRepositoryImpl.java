package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.data.DataPlatos;
import com.mixturaperuana.pe.exception.PlatoNoEncontradoException;
import com.mixturaperuana.pe.model.Plato;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlatoRepositoryImpl implements PlatoRepository {

    private Map<Integer, Plato> platos;

    public PlatoRepositoryImpl() {
        this.platos = DataPlatos.inicializar();
    }


    @Override
    public List<Plato> findAll() {
        return new ArrayList<>(platos.values());
    }

    @Override
    public Plato findById(int idPlato) throws PlatoNoEncontradoException {
        Plato plato = platos.get(idPlato);
        if (plato == null) {        throw new PlatoNoEncontradoException("El plato con ID " + idPlato + " no existe.");    }
        return plato;
    }

    @Override
    public void save(Plato plato) {

    }
}