package org.sunnycake.aton.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
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

	protected Session getSesion() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E getEntidadPorClave(PK clave) {
		return (E) getSesion().get(clasePersistente, clave);
	}

	public void guardarEntidad(E entidad) {
		getSesion().persist(entidad);
	}

	public void actualizarEntidad(E entidad) {
		getSesion().update(entidad);
	}

	public void eliminarEntidad(E entidad) {
		getSesion().delete(entidad);
	}

	protected Criteria crearCriteria() {
		return getSesion().createCriteria(clasePersistente);
	}

	public Set<E> getTodos() {
		return castLista(clasePersistente, crearCriteria().list());
	}

	/**
	 * Realiza una conversión de todos los elementos de la colección hacia una
	 * nueva lista, esto para evitar el warning que indica que puede que algunos
	 * elementos no sean de la misma clase.
	 * 
	 * @param clase
	 *            Clase a la que se desea convertir
	 * @param coleccion
	 *            Conjunto de elementos que se desean convertir
	 * @return List con elementos de la clase "clase"
	 */
	public static <T> Set<T> castLista(Class<? extends T> clase, Collection<?> coleccion) {
		Set<T> r = new HashSet<T>(coleccion.size());
		for (Object o : coleccion)
			r.add(clase.cast(o));
		return r;
	}
}