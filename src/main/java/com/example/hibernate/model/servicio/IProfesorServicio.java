package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Profesor;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public interface IProfesorServicio {
    List<Profesor> findAll();

    void delete(Integer profeId) throws InstanceNotFoundException;

    void crear(Profesor profe);

    void actualizar(Profesor profe);
}


