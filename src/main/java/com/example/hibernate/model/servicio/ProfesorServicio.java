package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Profesor;
import com.example.hibernate.model.dao.IProfesorDao;
import com.example.hibernate.model.dao.ProfesorDaoHibernate;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public class ProfesorServicio implements IProfesorServicio {

    private IProfesorDao profeDao;

    public ProfesorServicio() {
        this.profeDao = new ProfesorDaoHibernate();
    }

    public List<Profesor> findAll() {
        return this.profeDao.findAll();

    }

    public void delete(Integer profeId) throws InstanceNotFoundException {
        this.profeDao.remove(profeId);
    }

    @Override
    public void crear(Profesor profe) {
        this.profeDao.save(profe);
    }

    @Override
    public void actualizar(Profesor profe) {
        this.profeDao.update(profe);
    }
}
