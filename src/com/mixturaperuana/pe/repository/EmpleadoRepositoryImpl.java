package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.data.DataEmpleados;
import com.mixturaperuana.pe.model.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private Map<String, Empleado> empleados;

    public EmpleadoRepositoryImpl() {
        this.empleados = DataEmpleados.inicializar();
    }


    @Override
    public Empleado findByUsuario(String usuario) {
        return null;
    }

    @Override
    public List<Empleado> findAll() {
        return new ArrayList<>(empleados.values());
    }

    @Override
    public void save(Empleado empleado) {

    }
}
