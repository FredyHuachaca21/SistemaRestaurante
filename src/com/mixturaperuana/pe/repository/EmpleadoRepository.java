package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.model.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    Empleado findByUsuario(String usuario);
    List<Empleado> findAll();
    void save(Empleado empleado);
}
