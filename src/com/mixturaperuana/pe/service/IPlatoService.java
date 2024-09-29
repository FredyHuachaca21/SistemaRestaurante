package com.mixturaperuana.pe.service;

import com.mixturaperuana.pe.exception.PlatoNoEncontradoException;
import com.mixturaperuana.pe.model.Plato;

import java.util.List;

public interface IPlatoService {
    List<Plato> obtenerPlatosDisponibles();
    Plato obtenerPlatoPorId(int idPlato) throws PlatoNoEncontradoException;
}
