package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.exception.UsuarioNoEncontradoException;
import com.mixturaperuana.pe.model.Empleado;

import java.util.List;

public interface IEmpleadoService {
    Empleado login(String usuario, String contrasena) throws UsuarioNoEncontradoException;
    List<Empleado> getAllEmpleados();
}
