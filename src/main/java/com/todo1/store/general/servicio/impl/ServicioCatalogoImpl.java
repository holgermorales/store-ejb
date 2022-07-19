package com.todo1.store.general.servicio.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.todo1.store.CatalogoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.general.dao.CatalogoDao;
import com.todo1.store.general.servicio.ServicioCatalogo;
import com.todo1.store.modelo.general.Catalogo;

/**
 * @author holger.morales
 */
@Stateless
public class ServicioCatalogoImpl implements ServicioCatalogo {

    @EJB
    private CatalogoDao catalogoDao;

    /*
     * (non-Javadoc)
     * @see com.todo1.store.dao.general.servicio.ServicioCatalogo#obtenerPorGrupoEstado(java.lang.String, com.todo1.enumarados.EstadoRegistro)
     */
    @Override
    public List<CatalogoDTO> obtenerPorGrupoEstado(String grupo, EstadoRegistro estadoRegistro) throws GenericException {
        return this.catalogoDao.obtenerPorGrupoEstado(grupo, estadoRegistro);
    }

    @Override
    public Catalogo obtenerPorId(Long id) throws GenericException {
        final Catalogo catalogo = this.catalogoDao.findById(id);
        if (null == catalogo) {
            throw new GenericException("No se encuentra el catálogo con el código. Por favor contáctese con el administrado del Sistema para la revisión del catálogo").addInfo("Id", id);
        }
        return catalogo;
    }

    @Override
    public Catalogo obtenerPorNemonico(String nemonico) throws GenericException {
        return this.catalogoDao.obtenerPorNemonico(nemonico);
    }


}
