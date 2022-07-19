/**
 *
 */
package com.todo1.store.kardex.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.todo1.store.GaleriaDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.GaleriaDao;
import com.todo1.store.modelo.kardex.Galeria;

/**
 * @author holger.morales
 */
@Stateless
public class GaleriaDaoImpl extends GenericJpaDaoImpl<Galeria, Long> implements GaleriaDao {

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.dao.GaleriaDao#obtenerPorIdProducto(java.lang.Long)
     */
    @Override
    public List<GaleriaDTO> obtenerPorIdProducto(Long idProducto) throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select new ");
        jpql.append(GaleriaDTO.class.getCanonicalName());
        jpql.append(" ( ");
        jpql.append(" g.id, ");
        jpql.append(" g.imagen ");
        jpql.append(" ) ");
        jpql.append(" from Galeria g");
        jpql.append(" join g.producto p ");
        jpql.append(" where ");
        jpql.append(" p.id = :idProducto ");
        jpql.append(" and p.estadoRegistro = :estadoRegistro ");
        jpql.append(" and g.estadoRegistro = :estadoRegistro ");
        try {
            final TypedQuery<GaleriaDTO> tQuery = this.em.createQuery(jpql.toString(), GaleriaDTO.class);
            tQuery.setParameter("idProducto", idProducto);
            tQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            return tQuery.getResultList();
        } catch (final NoResultException e) {
            return Collections.emptyList();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al obtener la galería de un producto.", e).addInfo("Id producto", idProducto);
        }
    }

}
