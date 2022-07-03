package com.todo1.store.generic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import com.todo1.store.excepciones.DeleteException;
import com.todo1.store.excepciones.PersistException;
import com.todo1.store.excepciones.UpdateException;

/**
 * @author holger.morales
 */
public class GenericJpaDaoImpl<T, K extends Serializable> implements GenericJpaDao<T, K> {

    @PersistenceContext(unitName = "todo1-store-ejb")
    protected EntityManager em;
    private final Class<T> GenericClass;

    @SuppressWarnings("unchecked")
    public GenericJpaDaoImpl() {
        this.GenericClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.generic.GenericJpaDao#delete(java.io.Serializable)
     */
    @Override
    public void delete(K id) throws DeleteException {
        final T obj = this.findById(id);
        this.delete(obj);

    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.generic.GenericJpaDao#delete(java.lang.Object)
     */
    @Override
    public void delete(T entity) throws DeleteException {
        try {
            entity = this.em.merge(entity);
            this.em.remove(entity);
        } catch (final IllegalArgumentException | TransactionRequiredException ex) {
            throw new DeleteException(entity, ex);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.generic.GenericJpaDao#findById(java.io.Serializable)
     */
    @Override
    public T findById(K id) {
        return this.em.find(this.GenericClass, id);
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.generic.GenericJpaDao#persist(java.lang.Object)
     */
    @Override
    public T persist(T entity) throws PersistException {
        try {
            this.em.persist(entity);
            return entity;
        } catch (final EntityExistsException | IllegalArgumentException | TransactionRequiredException ex) {
            throw new PersistException(entity, ex);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.generic.GenericJpaDao#update(java.lang.Object)
     */
    @Override
    public T update(T entity) throws UpdateException {
        try {
            return this.em.merge(entity);
        } catch (final IllegalArgumentException | TransactionRequiredException ex) {
            throw new UpdateException(entity, ex);
        }
    }

}
