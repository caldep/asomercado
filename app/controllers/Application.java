package controllers;

import models.Listado;
import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

public class Application extends Controller {

    public static void index()
    {
        Listado ultimaLista = Listado.find("order by feCreacion desc").first();
        List<Listado> listados = Listado.find("order by feCreacion desc").from(1).fetch(10);
        render(ultimaLista,listados);
    }

}