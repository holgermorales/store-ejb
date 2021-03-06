/**
 *
 */
package com.todo1.store.kardex.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.todo1.store.ProductoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.ProductoDao;
import com.todo1.store.modelo.kardex.Producto;

/**
 * @author holger.morales
 */
@Stateless
public class ProductoDaoImpl extends GenericJpaDaoImpl<Producto, Long> implements ProductoDao {

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.dao.ProductoDao#obtenerTodos()
     */
    @Override
    public List<ProductoDTO> obtenerTodos(Integer pagina, Integer itemsPorPagina) throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select new ");
        jpql.append(ProductoDTO.class.getCanonicalName());
        jpql.append(" ( ");
        jpql.append(" p.id, ");
        jpql.append(" p.precio, ");
        jpql.append(" p.precioVentaPublico, ");
        jpql.append(" p.titulo, ");
        jpql.append(" p.descripcion, ");
        jpql.append(" p.stock, ");
        jpql.append(" p.codigo, ");
        jpql.append(" p.imagen, ");
        jpql.append(" c.id, ");
        jpql.append(" c.grupo, ");
        jpql.append(" c.nemonico, ");
        jpql.append(" c.nombre, ");
        jpql.append(" p.etiqueta, ");
        jpql.append(" p.colorEtiqueta ");
        jpql.append(" ) ");
        jpql.append(" from Producto p ");
        jpql.append(" join p.categoria c ");
        jpql.append(" where p.estadoRegistro = :estadoRegistro ");
        try {
            final TypedQuery<ProductoDTO> tQuery = this.em.createQuery(jpql.toString(), ProductoDTO.class);
            tQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            if (pagina > 0) {
                tQuery.setMaxResults(itemsPorPagina);
                tQuery.setFirstResult((pagina - 1) * itemsPorPagina);
            }
            return tQuery.getResultList();
        } catch (final NoResultException e) {
            return Collections.emptyList();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al obtener los productos", e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerPorCategoria(String categoria, Integer pagina, Integer itemsPorPagina) throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select new ");
        jpql.append(ProductoDTO.class.getCanonicalName());
        jpql.append(" ( ");
        jpql.append(" p.id, ");
        jpql.append(" p.precio, ");
        jpql.append(" p.precioVentaPublico, ");
        jpql.append(" p.titulo, ");
        jpql.append(" p.descripcion, ");
        jpql.append(" p.stock, ");
        jpql.append(" p.codigo, ");
        jpql.append(" p.imagen, ");
        jpql.append(" c.id, ");
        jpql.append(" c.grupo, ");
        jpql.append(" c.nemonico, ");
        jpql.append(" c.nombre, ");
        jpql.append(" p.etiqueta, ");
        jpql.append(" p.colorEtiqueta ");
        jpql.append(" ) ");
        jpql.append(" from Producto p ");
        jpql.append(" join p.categoria c ");
        jpql.append(" where p.estadoRegistro = :estadoRegistro ");
        jpql.append(" and c.nemonico = :nemonico ");
        try {
            final TypedQuery<ProductoDTO> tQuery = this.em.createQuery(jpql.toString(), ProductoDTO.class);
            tQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            tQuery.setParameter("nemonico", categoria);
            if (pagina > 0) {
                tQuery.setMaxResults(itemsPorPagina);
                tQuery.setFirstResult((pagina - 1) * itemsPorPagina);
            }
            return tQuery.getResultList();
        } catch (final NoResultException e) {
            return Collections.emptyList();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al obtener los productos", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.dao.ProductoDao#contarTodos()
     */
    @Override
    public Integer contarTodos() throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select count(p.id) ");
        jpql.append(" from Producto p ");
        jpql.append(" join p.categoria c ");
        jpql.append(" where p.estadoRegistro = :estadoRegistro ");
        try {
            final TypedQuery<Long> tQuery = this.em.createQuery(jpql.toString(), Long.class);
            tQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            return tQuery.getSingleResult().intValue();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al contar los productos", e);
        }
    }

    @Override
    public Integer contarPorCategoria(String categoria) throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select count(p.id) ");
        jpql.append(" from Producto p ");
        jpql.append(" join p.categoria c ");
        jpql.append(" where p.estadoRegistro = :estadoRegistro ");
        jpql.append(" and c.nemonico = :categoria ");
        try {
            final TypedQuery<Long> tQuery = this.em.createQuery(jpql.toString(), Long.class);
            tQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            tQuery.setParameter("categoria", categoria);
            return tQuery.getSingleResult().intValue();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al contar los productos", e);
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;

    }

}
