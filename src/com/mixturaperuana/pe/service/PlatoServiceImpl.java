package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.exception.PlatoNoEncontradoException;
import com.mixturaperuana.pe.model.Plato;
import com.mixturaperuana.pe.repository.PlatoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PlatoServiceImpl implements IPlatoService {

    private PlatoRepository platoRepository;

    public PlatoServiceImpl(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    @Override
    public List<Plato> obtenerPlatosDisponibles() {
        return platoRepository.findAll().stream().filter(Plato::isDisponible)            .collect(Collectors.toList());
    }

    @Override
    public Plato obtenerPlatoPorId(int idPlato) throws PlatoNoEncontradoException {
        return null;
    }
}
