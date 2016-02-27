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
 * @param <PK> Clave primaria
 * @param <E> Clase DTO
 */
public abstract class DAOAbstracto<PK extends Serializable, E> {

    /**
     * Mensajes al archivo de log.
     */
    final Logger logger;

    private final Class<E> clasePersistente;

    /**
     * Averigua la clase a la que se le está haciendo el DAO (Entidad) e
     * inicializa el logger con dicha clase.
     */
    @SuppressWarnings("unchecked")
    public DAOAbstracto() {
        this.clasePersistente = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
        this.logger = LogManager.getLogger(clasePersistente);
    }

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Sesion de Hibernate.
     *
     * @return Sesion.
     */
    protected Session getSesion() {
        logger.debug("Sesión creada");
        return sessionFactory.getCurrentSession();
    }

    /**
     * Obtiene la entidad entregando la clave primaria
     *
     * @param clave Clave primaria registrada en el DAO abstracto.
     * @return Entidad encontrada o nulo si no es encontrada.
     */
    @SuppressWarnings("unchecked")
    public E getEntidadPorClave(PK clave) {
        logger.debug("Buscando por clave " + clave);
        return (E) getSesion().get(clasePersistente, clave);
    }

    /**
     * Guardar una entidad en la base de datos.
     *
     * @param entidad Entidad a guardar.
     */
    public void guardarEntidad(E entidad) {
        logger.debug("Guardando la entidad: [" + clasePersistente + "]" + entidad);
        getSesion().persist(entidad);
    }

    /**
     * Actualiza la información de la entidad en la base de datos.
     *
     * @param entidad Entidad a actualizar
     */
    public void actualizarEntidad(E entidad) {
        logger.debug("Actualizando la entidad: [" + clasePersistente + "]" + entidad);
        getSesion().update(entidad);
    }

    /**
     * Elimina la entidad de la base de datos.
     *
     * @param entidad Entidad a eliminar.
     */
    public void eliminarEntidad(E entidad) {
        logger.debug("Eliminando la entidad: [" + clasePersistente + "]" + entidad);
        getSesion().delete(entidad);
    }

    /**
     * Crear criterio para la entidad.
     *
     * @return criteria para la entidad
     */
    protected Criteria crearCriteria() {
        logger.debug("Criteria creado");
        return getSesion().createCriteria(clasePersistente);
    }

    /**
     * Obtener todos los elementos de la base de datos.
     *
     * @return Set de elementos (Sin repetidos)
     */
    public Set<E> getTodos() {
        logger.debug("Obteniendo todos los [" + clasePersistente + "]");
        return castLista(clasePersistente, crearCriteria().list());
    }

    /**
     * Realiza una conversión de todos los elementos de la colección hacia una
     * nueva lista, esto para evitar el warning que indica que puede que algunos
     * elementos no sean de la misma clase.
     *
     * @param <T> Entidad
     * @param clase Clase a la que se desea convertir
     * @param coleccion Conjunto de elementos que se desean convertir
     * @return List con elementos de la clase "clase"
     */
    public static <T> Set<T> castLista(Class<? extends T> clase, Collection<?> coleccion) {
        Set<T> r = new HashSet<>(coleccion.size());
        for (Object o : coleccion) {
            r.add(clase.cast(o));
        }
        return r;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
