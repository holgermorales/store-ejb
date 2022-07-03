package com.todo1.store.general.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.todo1.store.CatalogoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.general.dao.CatalogoDao;
import com.todo1.store.general.servicio.ServicioCatalogo;

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

}
