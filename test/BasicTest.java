import models.Listado;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    //-----------------------------------------------------------------------------------

    @Before
    public void limpiarDatosPrueba()
    {
        Fixtures.deleteAll();
    }

    //-----------------------------------------------------------------------------------
    @Test
    public void creacionYConsultaListado()
    {
        // Se crea un listado
        new Listado("listado1","articulo de prueba.\\n articulo2.\\n").save();

        // Se consulta el listado creado
        Listado lista = Listado.find("byNombre","listado1").first();

        // Verifica la existencia de lista creada
        assertNotNull(lista);

        // Verifica el nombre de la lista
        assertEquals("listado1", lista.nombre);

        // Verifica el contenido de la lista
        assertEquals("articulo de prueba.\\n articulo2.\\n",lista.contenido);

    }
    //-----------------------------------------------------------------------------------
}
