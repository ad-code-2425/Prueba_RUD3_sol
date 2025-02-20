package com.example.hibernate.model.servicio;

import com.example.hibernate.model.Empleado;
import com.example.hibernate.model.dao.EmpleadoDaoHibernate;
import com.example.hibernate.model.dao.IEmpleadoDao;

public class EmpleadoServicio implements IEmpleadoServicio {

    private IEmpleadoDao empleadoDao;

    public EmpleadoServicio() {
        this.empleadoDao = new EmpleadoDaoHibernate();
    }

    @Override
    public Empleado find(Integer empno) {


       return this.empleadoDao.executarDentroTransaccion(()-> this.empleadoDao.find(empno));
    }

  

}
