/**
 *
 */
package com.todo1.store.kardex.dao;

import java.util.List;

import javax.ejb.Local;

import com.todo1.store.ProductoDTO;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.generic.dao.GenericJpaDao;
import com.todo1.store.modelo.kardex.Producto;

/**
 * @author holger.morales
 */
@Local
public interface ProductoDao extends GenericJpaDao<Producto, Long> {

    /**
     * Cuenta todos los productos existentes.
     *
     * @author holger.morales
     * @history Jul 3, 2022 - 6:45:26 PM holger.morales
     *          Versión inicial.
     * @return Total de registros {@code Integer}
     * @throws GenericException
     */
    public Integer contarTodos() throws GenericException;

    /**
     * Cuenta los productos por categoría
     *
     * @author holger.morales
     * @history Jul 3, 2022 - 6:45:26 PM holger.morales
     *          Versión inicial.
     * @param categoria
     * @return Total de registros {@code Integer}
     * @throws GenericException
     */
    public Integer contarPorCategoria(String categoria) throws GenericException;

    /**
     * Obtiene los productos paginados.
     *
     * @author holger.morales
     * @history Jul 3, 2022 - 6:40:08 PM holger.morales
     *          Versión inicial.
     * @param pagina
     * @param itemsPorPagina
     * @return Lista de {@code ProductoDTO}
     * @throws GenericException
     */
    public List<ProductoDTO> obtenerTodos(Integer pagina, Integer itemsPorPagina) throws GenericException;

    /**
     * Obtiene los productos por categoria.
     * @author holger.morales
     * @history Jul 16, 2022 - 12:59:26 PM holger.morales
     *          Versión inicial.
     * @param categoria
     * @param pagina
     * @param itemsPorPagina
     * @return
     * @throws GenericException
     */
    public List<ProductoDTO> obtenerPorCategoria(String categoria, Integer pagina, Integer itemsPorPagina) throws GenericException;

}
