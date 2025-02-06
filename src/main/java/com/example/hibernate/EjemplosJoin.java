package com.example.hibernate;

import java.util.List;

import com.example.hibernate.model.Direccion;

import com.example.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EjemplosJoin {

    public static void main(String[] args) {
        // Get the singleton instance of HibernateUtil
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();

        // Retrieve the SessionFactory
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        {
            System.out.println(
                    "----------- Q1: Uso de cross join: Cada fila de una tabla combinada con todas las filas de la otra tabla -----------");

            List<Object[]> datos = session.createSelectionQuery(
                    " select p.nombre, p.ape1, p.ape2, m.nombre FROM Profesor p, Modulo m ", Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " " + fila[1] + " " + fila[2] + " modulo: " + fila[3]);
            }
        }

        {
            System.out.println("-----------Q2: Uso de cross join filtrado con where y member of-----------");

            List<Object[]> datos = session.createSelectionQuery(
                    " select p.nombre, p.ape1, p.ape2, m.nombre FROM Profesor p, Modulo m where m member of p.modulos",
                    Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " " + fila[1] + " " + fila[2] + " modulo: " + fila[3]);
            }
        }

        {
            System.out.println(
                    "-----------Q3: Uso de inner join: Profesores y los módulos que imparten (Mismo resultado que arriba) -----------");

            List<Object[]> datos = session
                    .createSelectionQuery(
                            "select p.nombre, p.ape1, p.ape2, m.nombre FROM Profesor p inner join p.modulos m"
                            , Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " " + fila[1] + " " + fila[2] + " modulo: " + fila[3]);
            }
        }
        {
            System.out.println("-----------Q4: Uso de  join (sin inner) (Mismo resultado que con inner) -----------");

            List<Object[]> datos = session
                    .createSelectionQuery(
                            " select p.nombre, p.ape1, p.ape2, m.nombre FROM Profesor p  join p.modulos m",
                            Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " " + fila[1] + " " + fila[2] + " modulo: " + fila[3]);
            }
        }

        {
            System.out
                    .println("-----------Q5: Uso de join para obtener las dos entidades profesor y modulo-----------");

            List<Object[]> datos = session
                    .createSelectionQuery(" select p, m FROM Profesor p join p.modulos m", Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " modulo: " + fila[1]);
            }
        }

        {
            System.out.println(
                    "-----------Q6: Uso de left join: Todos los profesores con sus módulos o con null si no imparten módulos-----------");

            List<Object[]> datos = session
                    .createSelectionQuery(
                            " select p.nombre, p.ape1, p.ape2, m.nombre FROM Profesor p left join p.modulos m",
                            Object[].class)
                    .getResultList();

            for (Object[] fila : datos) {
                System.out.println("Profesor: " + fila[0] + " " + fila[1] + " " + fila[2] + " modulo: " + fila[3]);
            }
        }

        {
            System.out.println(
                    "-----------Q7: Uso de  join a través de las propiedades (Crea inner join. Esta es la forma recomendada.) -----------");

            List<Direccion> direcciones = session.createSelectionQuery(" select d FROM Direccion d join d.provincia p"
                    + " join p.comunidadautonoma ca where ca.nombre like 'Galicia'", Direccion.class).getResultList();

            for (Direccion dir : direcciones) {
                System.out.println("Direccion: " + dir);
            }
        }

        {
            System.out.println("-----------Q8: Uso directo de las propiedades (Crea cross join) -----------");

            List<Direccion> direcciones = session
                    .createSelectionQuery(
                            " select d FROM Direccion d where d.provincia.comunidadautonoma.nombre like 'Galicia'",
                            Direccion.class)
                    .getResultList();

            for (Direccion dir : direcciones) {
                System.out.println("Direccion: " + dir);
            }
        }

        session.close();
        sessionFactory.close();
    }

}