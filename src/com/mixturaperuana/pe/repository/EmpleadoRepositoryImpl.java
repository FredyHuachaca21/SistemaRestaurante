package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.data.DataEmpleados;
import com.mixturaperuana.pe.model.Empleado;

import java.util.List;
import java.util.Map;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private Map<String, Empleado> empleados;

    public EmpleadoRepositoryImpl() {
        this.empleados = DataEmpleados.inicializar();
    }


    @Override
    public Empleado findByUsuario(String usuario) {
        return empleados.get(usuario);
    }

    @Override
    public List<Empleado> findAll() {
        return List.of();
    }

    @Override
    public void save(Empleado empleado) {
        empleados.put(empleado.getUsuario(), empleado);
    }
}
