package org.sunnycake.aton.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementación de las funciones compartidas por todos los DAO, permitiendo la
 * reutilización de código.
 * 
 * @author Camilo Sampedro
 *
 * @param <PK>
 *            Clave primaria
 * @param <E>
 *            Clase DTO
 */
public abstract class DAOAbstracto<PK extends Serializable, E> {

	protected final Logger logger;

	private final Class<E> clasePersistente;

	@SuppressWarnings("unchecked")
	public DAOAbstracto() {
		this.clasePersistente = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		this.logger = LogManager.getLogger(clasePersistente);
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E getByKey(PK clave) {
		return (E) getSession().get(clasePersistente, clave);
	}

	public void persist(E entidad) {
		getSession().persist(entidad);
	}

	public void update(E entidad) {
		getSession().update(entidad);
	}

	public void delete(E entidad) {
		getSession().delete(entidad);
	}

	protected Criteria crearCriteria() {
		return getSession().createCriteria(clasePersistente);
	}

	public Set<E> getAll() {
		return castList(clasePersistente, crearCriteria().list());
	}

	public static <T> Set<T> castList(Class<? extends T> clazz, Collection<?> c) {
		Set<T> r = new HashSet<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
}