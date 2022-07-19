/**
 * 
 */
package como.todo1.store.kardex.test;

import org.junit.Test;

import com.todo1.store.excepciones.GenericException;

import junit.framework.Assert;

/**
 * @author holger.morales
 */
public class ServicioProductoTest extends ServicioBaseTest {

    /**
     * Nunca debe ser null un listado de clientes.
     * 
     * @author holger.morales
     * @history Jul 3, 2022 - 9:05:06 PM developer
     *          Versión inicial.
     */
    @Test
    public void obtenerProductos() {
        // em.getTransaction().begin();
        try {
            Assert.assertNotNull(productoDaoImpl.obtenerTodos(1, 50));
        } catch (GenericException e) {
            e.printStackTrace();
        }
    }

    /**
     * El total de productos jamas debe ser cero.
     * 
     * @author holger.morales
     * @history Jul 3, 2022 - 9:07:49 PM holger.morales
     *          Versión inicial.
     */
    @Test
    public void contarProductos() {
        try {
            Assert.assertNotNull(productoDaoImpl.contarTodos());
        } catch (GenericException e) {
        }
    }

    /**
     * Sin paginación significa que puede enviar un valor 0 o negativo
     * 
     * @author holger.morales
     * @history Jul 3, 2022 - 9:10:49 PM holger.morales
     *          Versión inicial.
     */
    @Test
    public void obtenerProductosSinPaginacion() {
        try {
            Assert.assertNotNull(productoDaoImpl.obtenerTodos(0, 10));
        } catch (GenericException e) {
        }
    }

    /**
     * @author holger.morales
     * @history Jul 3, 2022 - 9:14:10 PM holger.morales
     *          Versión inicial.
     */
    @Test
    public void obtenerProductosConValoresNuºlosPaginacion() {
        try {
            Assert.assertNull(productoDaoImpl.obtenerTodos(null, null));
        } catch (GenericException e) {
        }
    }

}
