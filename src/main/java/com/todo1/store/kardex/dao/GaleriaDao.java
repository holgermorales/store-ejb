/**
 *
 */
package com.todo1.store.kardex.dao;

import java.util.List;

import javax.ejb.Local;

import com.todo1.store.GaleriaDTO;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.generic.dao.GenericJpaDao;
import com.todo1.store.modelo.kardex.Galeria;

/**
 * @author holger.morales
 */
@Local
public interface GaleriaDao extends GenericJpaDao<Galeria, Long> {

    /**
     * Obtienes la galeria de un producto.
     *
     * @author holger.morales
     * @history Jul 3, 2022 - 6:24:21 PM holger.morales
     *          Versión inicial.
     * @param idProducto
     *            {@Code Long}
     * @return Lista de {@code GaleriaDTO}
     * @throws GenericException
     */
    public List<GaleriaDTO> obtenerPorIdProducto(Long idProducto) throws GenericException;

}
