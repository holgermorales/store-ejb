package com.todo1.store.generic.dao;

import java.io.Serializable;

import com.todo1.store.excepciones.DeleteException;
import com.todo1.store.excepciones.PersistException;
import com.todo1.store.excepciones.UpdateException;

/**
 * @author holger.morales
 */
public interface GenericJpaDao<T, K extends Serializable> {
    /**
     * Delete a entity by id.
     *
     * @author holger.morales
     * @history Jul 2, 2022 - 1:43:46 AM holger.morales
     *          Versión inicial.
     * @param id
     * @throws DeleteException
     */
    public void delete(K id) throws DeleteException;

    /**
     * Delete a entity
     *
     * @author holger.morales
     * @history Jul 2, 2022 - 1:44:53 AM holger.morales
     *          Versión inicial.
     * @param entity
     * @throws DeleteException
     */
    public void delete(T entity) throws DeleteException;

    /**
     * Get a entity.
     * 
     * @author holger.morales
     * @history Jul 2, 2022 - 1:59:20 AM holger.morales
     *          Versión inicial.
     * @param id
     * @return
     */
    public T findById(K id);

    /**
     * Persit a entity.
     *
     * @author holger.morales
     * @history Jul 2, 2022 - 1:51:58 AM holger.morales
     *          Versión inicial.
     * @param entity
     * @return
     * @throws PersistException
     */
    public T persist(T entity) throws PersistException;

    /**
     * Update a entity.
     *
     * @author holger.morales
     * @history Jul 2, 2022 - 1:52:20 AM holger.morales
     *          Versión inicial.
     * @param entity
     * @return
     * @throws UpdateException
     */
    public T update(T entity) throws UpdateException;
}
