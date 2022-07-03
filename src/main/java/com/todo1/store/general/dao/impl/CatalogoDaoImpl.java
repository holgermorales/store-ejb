package com.todo1.store.general.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.todo1.store.CatalogoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.general.dao.CatalogoDao;
import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.modelo.general.Catalogo;

/**
 * @author holger.morales
 */
@Stateless
public class CatalogoDaoImpl extends GenericJpaDaoImpl<Catalogo, Long> implements CatalogoDao {

    @Override
    public List<CatalogoDTO> obtenerPorGrupoEstado(String grupo, EstadoRegistro estadoRegistro) throws GenericException {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("select new ");
        jpql.append(CatalogoDTO.class.getCanonicalName());
        jpql.append(" ( ");
        jpql.append(" c.id, ");
        jpql.append(" c.grupo, ");
        jpql.append(" c.nemonico, ");
        jpql.append(" c.nombre, ");
        jpql.append(" c.descripcion, ");
        jpql.append(" c.visible ");
        jpql.append(" ) ");
        jpql.append(" from Catalogo c ");
        jpql.append(" where c.grupo = :grupo ");
        jpql.append("and c.estadoRegistro = :estadoRegistro ");
        jpql.append("order by c.nombre ");
        try {
            final TypedQuery<CatalogoDTO> tQuery = this.em.createQuery(jpql.toString(), CatalogoDTO.class);
            tQuery.setParameter("grupo", grupo);
            tQuery.setParameter("estadoRegistro", estadoRegistro.toString());
            return tQuery.getResultList();
        } catch (final NoResultException e) {
            return Collections.emptyList();
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al obtener el catálogo.", e).addInfo("Grupo", grupo).addInfo("Estado registro", estadoRegistro.toString());
        }
    }

}
