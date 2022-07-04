/**
 * 
 */
package com.todo1.store.kardex.servicio;

import java.util.List;

import javax.ejb.Local;

import com.todo1.store.GaleriaDTO;
import com.todo1.store.excepciones.GenericException;

/**
 * @author holger.morales
 */
@Local
public interface ServicioGaleria {

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
