package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Empleado;
import com.example.hibernate.model.dao.IEmpleadoDao;
import com.example.hibernate.model.dao.EmpleadoDaoHibernate;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public class EmpleadoServicio implements IEmpleadoServicio {

    private IEmpleadoDao empleadoDao;

    public EmpleadoServicio() {
        this.empleadoDao = new EmpleadoDaoHibernate();
    }

    public List<Empleado> findAll() {
        List<Empleado> empleados = (List<Empleado>) this.empleadoDao
                .executarDentroTransaccion(() -> {
                    return empleadoDao.findAll();
                });
        return empleados;
    }

    public void delete(Integer profeId) throws InstanceNotFoundException {
        this.empleadoDao.executarDentroTransaccion(() -> {
            empleadoDao.remove(profeId);
            return null;
        });
    }

    @Override
    public void crear(Empleado profe) {
        this.empleadoDao.executarDentroTransaccion(() -> {
            this.empleadoDao.create(profe);
            return null;
        });
    }

    @Override
    public void actualizar(Empleado profe) {
        this.empleadoDao.executarDentroTransaccion(() -> {
            this.empleadoDao.update(profe);
            return null;
        });
    }

}
