/**
 *
 */
package com.todo1.store.kardex.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.todo1.store.GaleriaDTO;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.kardex.dao.GaleriaDao;
import com.todo1.store.kardex.servicio.ServicioGaleria;

/**
 * @author holger.morales
 */
@Stateless
public class ServicioGaleriaImpl implements ServicioGaleria {

    @EJB
    private GaleriaDao galeriaDao;

    @Override
    public List<GaleriaDTO> obtenerPorIdProducto(Long idProducto) throws GenericException {
        return this.galeriaDao.obtenerPorIdProducto(idProducto);
    }

}
