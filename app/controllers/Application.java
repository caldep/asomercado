package controllers;

import models.Listado;
import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

public class Application extends Controller {

    public static void index(Long idListado)
    {
        Listado ultimaLista;
        List<Listado> listados;

        if(idListado != null)
        {
            ultimaLista    = Listado.find("byId",idListado).first();
            listados = Listado.find("where id<>",idListado).from(0).fetch(10);
        }
        else
        {
            ultimaLista    = Listado.find("order by feCreacion desc").first();
            listados = Listado.find("order by feCreacion desc").from(1).fetch(10);
        }

        render(ultimaLista,listados);
    }
    //-------------------------------------------------------------------------------

    public static void show(Long id){
        Listado listaMostrar = Listado.findById(id);
        render(listaMostrar);
    }
}