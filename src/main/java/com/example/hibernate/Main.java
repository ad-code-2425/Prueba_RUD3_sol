package com.example.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.hibernate.model.ComunidadAutonoma;
import com.example.hibernate.model.Modulo;
import com.example.hibernate.model.Profesor;
import com.example.hibernate.model.Provincia;
import com.example.hibernate.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of HibernateUtil
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();

        // Retrieve the SessionFactory
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        int idCA = 2118;

        // probarOpcionCascade(session, idCA);
        probarOrphanRemoval(session, idCA);

        // // Probamos el problema de la N+1 consultas
        // {

        // List<Profesor> profes = session.createQuery("select p from Profesor p order
        // by p.id").list();
        // int contador_profes = 0;

        // for (Profesor profesor : profes) {
        // contador_profes++;
        // for (Modulo modulo : profesor.getModulos()) {

        // System.out.println(
        // "Profesor" + contador_profes + ": " + profesor.getNombre() + " " +
        // profesor.getId() + ": "
        // + modulo.getNombre());
        // }
        // }

        // }

        session.close();

        sessionFactory.close();

    }

    private static void probarOpcionCascade(Session session, int idCA) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Creamos una comunidad
            ComunidadAutonoma ca = new ComunidadAutonoma();

            ca.setIdCa(idCA);
            ca.setNombre("Mi nueva ca");

            // Le asociamos una provincia a través del método addProvincia que ya crea las
            // relaciones bidireccionales:
            ca.addProvincia(new Provincia(idCA, ca, "nueva prov asociada"));

            // Guardamos la comunidad. Si existe CascadeType.PERSIST, se insertarán también
            // las provincias
            // Si no existe, no se guardan las provincias asociadas
            session.persist(ca);

            // Eliminamos la comunidad y se eliminarán las provincias si tenemos la opción
            // CascadeType.REMOVE
            // Si no existe esa opción: org.hibernate.TransientObjectException: persistent
            // instance references an unsaved transient instance of
            // 'com.example.hibernate.model.ComunidadAutonoma' (save the transient instance
            // before flushing)
            session.remove(ca);

            tx.commit();

            System.out.println("Se ha terminado la tx con éxito");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ha ocurrido una exception: " + ex.getMessage());
            if (tx != null) {
                tx.rollback();
            }

        }

    }

    private static void probarOrphanRemoval(Session session, int idCA) {

        // Probamos orphanRemoval
        // Para ello, dejamos las opciones cascade de persist y eliminamos la de remove en ComunidadAutonoma.java
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Creamos una comunidad
            ComunidadAutonoma ca = new ComunidadAutonoma();
            ca.setIdCa(idCA);
            ca.setNombre("Mi nueva ca");

            // Le asociamos una nueva provincia a través del método addProvincia que ya crea las
            // relaciones bidireccionales:
            ca.addProvincia(new Provincia(idCA, ca, "nueva prov asociada"));

            session.persist(ca); //Con CascadeType.PERSIST se insertarán también las provincias

            // Recuperamos la CA
            ComunidadAutonoma caRecuperada = session.get(ComunidadAutonoma.class, idCA);
            if (caRecuperada != null) {
                Provincia prov = caRecuperada.getProvincias().iterator().next();              

                //Eliminamos de forma bidireccional la provincia de la colección y estableciendo null su propiedad CA
               caRecuperada.removeProvincia(prov);
            }

            tx.commit();

            System.out.println("Se ha terminado la tx con éxito");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ha ocurrido una exception: " + ex.getMessage());
            if (tx != null) {
                tx.rollback();
            }

        }
    }

}
