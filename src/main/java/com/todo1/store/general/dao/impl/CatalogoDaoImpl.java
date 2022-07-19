package com.todo1.store.general.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

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

    @Override
    public Catalogo obtenerPorNemonico(String nemonico) throws GenericException {
        final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
        final CriteriaQuery<Catalogo> criteriaQuery = criteriaBuilder.createQuery(Catalogo.class);
        final Root<Catalogo> catalogoRoot = criteriaQuery.from(Catalogo.class);
        criteriaQuery.select(catalogoRoot);
        final List<Predicate> criteria = new ArrayList<>();

        final ParameterExpression<String> paramNemonico = criteriaBuilder.parameter(String.class, "nemonico");
        criteria.add(criteriaBuilder.equal(catalogoRoot.get("nemonico"), paramNemonico));

        final ParameterExpression<String> paramEstado = criteriaBuilder.parameter(String.class, "estadoRegistro");
        criteria.add(criteriaBuilder.equal(catalogoRoot.get("estadoRegistro"), paramEstado));

        criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));

        try {
            final TypedQuery<Catalogo> typedQuery = this.em.createQuery(criteriaQuery);
            typedQuery.setParameter("nemonico", nemonico);
            typedQuery.setParameter("estadoRegistro", EstadoRegistro.ACTIVO.toString());
            return typedQuery.getSingleResult();
        } catch (final NoResultException e) {
            throw new GenericException("No se puede determinar el catálogo.").addInfo("Nemónico", nemonico);
        } catch (final NonUniqueResultException e) {
            throw new GenericException("Existe mas de un registro para el mismo catálogo. Por favor contáctese con el Administrado del Sistema.").addInfo("Nemónico", nemonico);
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al obtener el catálogo", e).addInfo("Nemónico", nemonico);
        }
    }

}
