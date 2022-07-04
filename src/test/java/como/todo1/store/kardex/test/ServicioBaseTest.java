/**
 * 
 */
package como.todo1.store.kardex.test;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import com.todo1.store.kardex.dao.impl.ProductoDaoImpl;
import com.todo1.store.kardex.servicio.impl.ServicioProductoImpl;

/**
 * @author holger.morales
 */
public abstract class ServicioBaseTest {
    static EntityManager em;

    // Daos
    static ProductoDaoImpl productoDaoImpl = new ProductoDaoImpl();

    // Servicios
    static ServicioProductoImpl servicioProductoImp = new ServicioProductoImpl();

    @After
    public void pseudoBlueprintContainerClose() {

        if (em != null) {
            em.close();
            System.out.println("Se cierra la conexion");
        } else {
            System.out.println("No existe enterprise manager");
        }

    }

    @Before
    public void setup() {
        final Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:6437/todo1_store");
        props.put("hibernate.connection.username", "todo1");
        props.put("hibernate.connection.password", "*****");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("todo1-store-ejb", props);
        em = factory.createEntityManager();
        // productoDaoImpl.setEm(em);

    }

}
