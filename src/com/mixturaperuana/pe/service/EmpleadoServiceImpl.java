package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.exception.UsuarioNoEncontradoException;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.repository.EmpleadoRepository;

import java.util.List;

public class EmpleadoServiceImpl implements IEmpleadoService {
    private EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado login(String usuario, String contrasena) throws UsuarioNoEncontradoException {
        return null;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return List.of();
    }
}
