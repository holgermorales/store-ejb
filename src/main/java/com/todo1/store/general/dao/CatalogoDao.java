package com.todo1.store.general.dao;

import java.util.List;

import javax.ejb.Local;

import com.todo1.store.CatalogoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.generic.dao.GenericJpaDao;
import com.todo1.store.modelo.general.Catalogo;

/**
 * @author holger.morales
 */
@Local
public interface CatalogoDao extends GenericJpaDao<Catalogo, Long> {
    /**
     * Obtiene los catálogos por grupo y estado.
     *
     * @author holger.morales
     * @history Jul 2, 2022 - 11:26:43 AM holger.morales
     *          Versión inicial.
     * @param grupo
     *            Grupo del catálogo.
     * @param estadoRegistro
     *            Estado del registro {@link EstadoRegistro}
     * @return Listado de catálogos. {@link CatalogoDTO}
     * @throws GenericException
     */
    public List<CatalogoDTO> obtenerPorGrupoEstado(String grupo, EstadoRegistro estadoRegistro) throws GenericException;
}
