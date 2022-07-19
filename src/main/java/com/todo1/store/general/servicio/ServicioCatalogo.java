package com.todo1.store.general.servicio;

import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.todo1.store.CatalogoDTO;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.modelo.general.Catalogo;

/**
 * @author holger.morales
 */
@Local
public interface ServicioCatalogo {

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

    /**
     * @author holger.morales
     * @history Jul 16, 2022 - 11:26:43 AM holger.morales
     *          Versión inicial.
     * @param id Id del catálgo
     * @return {@link Catalogo}
     * @throws GenericException
     */
    public Catalogo obtenerPorId(Long id) throws GenericException;

    /**
     * Obtiene el catálogo por el nemónico.
     * @author holger.morales
     * @history Jul 16, 2022 - 11:26:43 AM holger.morales
     *          Versión inicial.
     * @param nemonico
     * @return {@link Catalogo}
     * @throws GenericException
     */
    public Catalogo obtenerPorNemonico(String nemonico) throws GenericException;

}
