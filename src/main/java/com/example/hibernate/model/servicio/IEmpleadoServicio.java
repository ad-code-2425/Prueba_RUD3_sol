package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Empleado;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public interface IEmpleadoServicio {
    List<Empleado> findAll();

    void delete(Integer profeId) throws InstanceNotFoundException;

    void crear(Empleado profe);

    void actualizar(Empleado profe);
}


