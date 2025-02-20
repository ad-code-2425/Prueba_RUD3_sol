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
        List<Profesor> profesores = (List<Profesor>) this.profeDao
                .executarDentroTransaccion(() -> {
                    return profeDao.findAll();
                });
        return profesores;
    }

    public void delete(Integer profeId) throws InstanceNotFoundException {
        this.profeDao.executarDentroTransaccion(() -> {
            profeDao.remove(profeId);
            return null;
        });
    }

    @Override
    public void crear(Profesor profe) {
        this.profeDao.executarDentroTransaccion(() -> {
            this.profeDao.create(profe);
            return null;
        });
    }

    @Override
    public void actualizar(Profesor profe) {
        this.profeDao.executarDentroTransaccion(() -> {
            this.profeDao.update(profe);
            return null;
        });
    }

}
