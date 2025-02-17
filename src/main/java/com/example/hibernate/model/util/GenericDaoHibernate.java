package com.example.hibernate.model.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;
import com.example.hibernate.util.HibernateUtil;

/**
 *
 * @author maria
 */
public class GenericDaoHibernate<E, PK extends Serializable> implements IGenericDao<E, PK> {

	// getClass(): accedemos a la clase de la instancia que extienda esta clase
	// (será DepartamentoSQLServerDao u XSQLServerDao)
	// .getGenericSuperclass(): obtenemos el tipo de la clase madre directa:
	// GenericDaoHibernate En el caso de que sea una clase parametrizada (con
	// Generics),devuelve el tipo del parámetro de tipo E: ParameterizedType:
	// https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#getGenericSuperclass--
	// .getActualTypeArguments(): devuelve un array de los tipos de los argumentos
	// que se le pasan al tipo parametrizado <E, PK>
	// finalmente obtenemos el nombre del tipo parametrizado: <E> que será
	// Departamento (o empleado cuando se implemente)

	private Class<E> entityClass;

	private HibernateUtil hibernateUtil;
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		this.hibernateUtil = HibernateUtil.getInstance();

		// Retrieve the SessionFactory
		this.sessionFactory = hibernateUtil.getSessionFactory();

	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void save(E entity) {

		Transaction tx = null;

		try {
			tx = getSession().beginTransaction();

			getSession().persist(entity);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (this.getSession() != null) {
				this.getSession().close();

			}
		}

	}

	public E update(E entity) {

		E entityMerged = null;

		Transaction tx = null;

		try {
			tx = getSession().beginTransaction();

			entityMerged = getSession().merge(entity);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (this.getSession() != null) {
				this.getSession().close();

			}
		}
		return entityMerged;

	}

	public boolean exists(PK id) {
		E entity = null;
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			entity = getSession().get(entityClass, id);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (this.getSession() != null) {
				this.getSession().close();

			}
		}
		return entity != null;
	}

	public E find(PK id) throws InstanceNotFoundException {
		E entity = null;
		entity = (E) getSession().get(entityClass, id);
		if (entity == null) {
			throw new InstanceNotFoundException(id, entityClass.getName());
		}

		return entity;

	}

	public void remove(PK id) throws InstanceNotFoundException {

		Transaction tx = null;

		try {
			tx = getSession().beginTransaction();

			getSession().remove(find(id));

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (this.getSession() != null) {
				this.getSession().close();

			}
		}

	}

	@Override
	public List<E> findAll() {
		Transaction tx = null;
		List<E> listado = null;
		try {
			tx = getSession().beginTransaction();

			listado = (List<E>) getSession()
					.createSelectionQuery(" select c FROM " + entityClass.getName() + " c", entityClass)
					.getResultList();

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (this.getSession() != null) {
				this.getSession().close();

			}
		}

		return listado;

	}

}