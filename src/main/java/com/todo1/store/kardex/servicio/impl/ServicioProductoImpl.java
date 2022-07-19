/**
 *
 */
package com.todo1.store.kardex.servicio.impl;

import com.todo1.store.ProductoDTO;
import com.todo1.store.enumerados.CategoriaProducto;
import com.todo1.store.enumerados.EstadoRegistro;
import com.todo1.store.excepciones.GenericException;
import com.todo1.store.excepciones.PersistException;
import com.todo1.store.general.servicio.ServicioCatalogo;
import com.todo1.store.kardex.dao.ProductoDao;
import com.todo1.store.kardex.servicio.ServicioGaleria;
import com.todo1.store.kardex.servicio.ServicioProducto;
import com.todo1.store.modelo.general.Catalogo;
import com.todo1.store.modelo.kardex.Producto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author holger.morales
 */
@Stateless
public class ServicioProductoImpl implements ServicioProducto {

    @EJB
    private ProductoDao productoDao;

    @EJB
    private ServicioGaleria servicioGaleria;

    @EJB
    private ServicioCatalogo servicioCatalogo;

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.servicio.ServicioProducto#contarTodos()
     */
    @Override
    public Integer contarTodos() throws GenericException {
        return this.productoDao.contarTodos();
    }

    @Override
    public Integer contarPorCategoria(String categoria) throws GenericException {
        if (CategoriaProducto.TODOS.toString().equalsIgnoreCase(categoria)) {
            return this.productoDao.contarTodos();
        }
        return this.productoDao.contarPorCategoria(categoria);
    }

    @Override
    public void registrar(ProductoDTO productoDTO, String login, LocalDateTime fechaCrea) throws GenericException {
        final Catalogo categoriaProduco = this.servicioCatalogo.obtenerPorNemonico(productoDTO.getCategoria().getNemonico());
        final Producto producto = new Producto();
        producto.setCodigo(productoDTO.getCodigo());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setPrecioVentaPublico(productoDTO.getPrecioVentaPublico());
        producto.setTitulo(productoDTO.getTitulo());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setStock(productoDTO.getStock());
        producto.setImagen(productoDTO.getImagen());
        producto.setCategoria(categoriaProduco);
        producto.setEstadoRegistro(EstadoRegistro.ACTIVO.toString());
        producto.setEtiqueta(productoDTO.getEtiqueta().getEtiqueta());
        producto.setColorEtiqueta(productoDTO.getEtiqueta().getColorEtiqueta());
        producto.setUsuarioCrea(login);
        producto.setUsuarioModifica(login);
        producto.setFechaCrea(fechaCrea);
        producto.setFechaModifica(fechaCrea);
        try {
            this.productoDao.persist(producto);
        } catch (final Exception e) {
            throw new GenericException("Se produjo un error al registrar los datos del producto.", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.todo1.store.kardex.servicio.ServicioProducto#obtenerPorCategoria(java.lang.String)
     */
    @Override
    public List<ProductoDTO> obtenerPorCategoria(String categoria, Integer pagina, Integer itemsPorPagina) throws GenericException {
        if (CategoriaProducto.TODOS.toString().equalsIgnoreCase(categoria)) {
            return this.productoDao.obtenerTodos(pagina, itemsPorPagina);
        }
        return this.productoDao.obtenerPorCategoria(categoria, pagina, itemsPorPagina);
    }

    @Override
    public List<ProductoDTO> obtenerTodosSinGaleria(Integer pagina, Integer itemsPorPagina) throws GenericException {
        return this.productoDao.obtenerTodos(pagina, itemsPorPagina);
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
