package com.example.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.hibernate.model.Modulo;
import com.example.hibernate.model.Profesor;
import com.example.hibernate.util.HibernateUtil;

public class MainNMas1 {
    public static void main(String[] args) {
        // Get the singleton instance of HibernateUtil
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();

        // Retrieve the SessionFactory
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        List<Profesor> profes = session
                .createSelectionQuery("select p from Profesor p order        by p.id", Profesor.class).getResultList();
        int contador_profes = 0;

        for (Profesor profesor : profes) {
            contador_profes++;
            for (Modulo modulo : profesor.getModulos()) {

                System.out.println(
                        "Profesor" + contador_profes + ": " + profesor.getNombre() + " " +
                                profesor.getId() + ": "
                                + modulo.getNombre());
            }
        }

        session.close();

        sessionFactory.close();

    }
}
