import models.Articulo;
import models.Listado;
import org.junit.*;
import java.util.*;
import java.util.List;

import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    //-----------------------------------------------------------------------------------

    @Before
    public void limpiarDatosPrueba()
    {
        Fixtures.deleteAll();
        Fixtures.deleteAllModels();
    }

    //-----------------------------------------------------------------------------------
    @Test
    public void creacionYConsultaLista()
    {
        // Se crea un listado
        new Listado("lista1").save();

        // Se consulta el listado creado
        Listado lista = Listado.find("byNombre","lista1").first();

        // Verifica la existencia de lista creada
        assertNotNull(lista);

        // Verifica el nombre de la lista
        assertEquals("lista1", lista.nombre);

    }
    //-----------------------------------------------------------------------------------
    @Test
    public void agregarArticulosLista()
    {
        // Se crea una lista
        Listado lista = new Listado("lista2").save();

        // verifica la creación de la lista
        assertEquals("lista2",lista.nombre);

        // Se agregan artículos
        new Articulo(lista,"Tomate").save();
        new Articulo(lista,"Cebolla").save();

        List<Articulo> articulos = Articulo.find("byLista",lista).fetch();

        // Verifica la cantidad de articulos
        assertEquals(2,articulos.size());

        // Verifica contenido del primer articulo
        Articulo primerArticulo = articulos.get(0);
        assertEquals("Tomate", primerArticulo.descripcion);

        // Verifica contenido del segundo articulo
        Articulo segundoArticulo = articulos.get(1);
        assertEquals("Cebolla", segundoArticulo.descripcion);

    }
    //-----------------------------------------------------------------------------------

    @Test
    public void relacionArticulosLista() {
        // Crear lista
        Listado lista = new Listado("lista3").save();

        // Agregar articulos
        lista.addArticulo("Carne");
        lista.addArticulo("Pollo");

        // Verifica el numero de registros
        assertEquals(1, lista.count());
        assertEquals(2, Articulo.count());

        // Verifica articulos
        assertEquals(2, lista.articulos.size());
        assertEquals("Carne", lista.articulos.get(0).descripcion);

        // Elimina la lista
        lista.delete();


        // Verificar la eliminacion de los articulos
        assertEquals(0, Listado.count());
        assertEquals(0, Articulo.count());
    }
    //-----------------------------------------------------------------------------------

    @Test
    public void revisarVariasListasConArticulos() {
        Fixtures.load("data.yml");

        // contabiliza registros
        assertEquals(4, Listado.count());
        assertEquals(10, Articulo.count());

        // consulta las listas1
        List<Listado> listas = Listado.find("byNombre", "listado1").fetch();
        assertEquals(1, listas.size());

        // Solicita los articulos de la lista1
        List<Articulo> articulos = Articulo.find("lista.nombre", "listado1").fetch();
        assertEquals(4, articulos.size());

        // Encuentra la última lista modificada
        Listado ultimaListaModificada = Listado.find("order by feModificacion desc").first();
        assertNotNull(ultimaListaModificada);
        assertEquals("listado3", ultimaListaModificada.nombre);

        // verifica que la última lista modificada tenga 3 articulos
        assertEquals(3, ultimaListaModificada.articulos.size());

        // Agrega un nuevo artículo a la lista
        ultimaListaModificada.addArticulo("Cerdo");
        assertEquals(4, ultimaListaModificada.articulos.size());
        assertEquals(11, Articulo.count());
    }
}
