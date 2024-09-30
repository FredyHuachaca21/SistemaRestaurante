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
        Empleado empleado = empleadoRepository.findByUsuario(usuario);
        if (empleado != null && empleado.getContrasena().equals(contrasena)) {
            return empleado;
        } else {
            throw new UsuarioNoEncontradoException("Credenciales inválidas.");
        }
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }
}
