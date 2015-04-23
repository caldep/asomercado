package controllers;

import play.mvc.*;
import play.*;

import models.Listado;

import java.util.*;

/**
 * Created by caldep on 22/04/15.
 */
public class ListadoController extends Controller {

    public static void show(Long id){
        models.Listado listaMostrar = models.Listado.findById(id);
        render(listaMostrar);
    }
}
