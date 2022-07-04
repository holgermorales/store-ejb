/**
 *
 */
package com.todo1.store.kardex.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.todo1.store.ProductoDTO;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.kardex.dao.ProductoDao;
import com.todo1.store.kardex.servicio.ServicioGaleria;
import com.todo1.store.kardex.servicio.ServicioProducto;

/**
 * @author holger.morales
 */
@Stateless
public class ServicioProductoImpl implements ServicioProducto {

    @EJB
    private ProductoDao productoDao;

    @EJB
    private ServicioGaleria servicioGaleria;

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.servicio.ServicioProducto#contarTodos()
     */
    @Override
    public Integer contarTodos() throws GenericException {
        return this.productoDao.contarTodos();
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.servicio.ServicioProducto#obtenerTodos(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ProductoDTO> obtenerTodos(Integer pagina, Integer itemsPorPagina) throws GenericException {
        final List<ProductoDTO> productos = this.productoDao.obtenerTodos(pagina, itemsPorPagina);
        for (final ProductoDTO productoDTO : productos) {
            productoDTO.setGalerias(this.servicioGaleria.obtenerPorIdProducto(productoDTO.getId()));
        }
        return productos;
    }

}
