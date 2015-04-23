package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index(Long idListado)
    {
        Listado ultimaLista;
        List<Listado> listados;

        if(idListado != null)
        {
            ultimaLista    = Listado.find("byId",idListado).first();
            listados = Listado.find("id <> ?",idListado).from(0).fetch(10);
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
    //--------------------------------------------------------------------------------
    public static void guardarArticulo(Long idArticulo, String descripcion, Long idLista){

        Articulo item = Articulo.findById(idArticulo);
        Listado lista = Listado.findById(idLista);
        lista.feModificacion = new Date();
        //verifica que el articulo exista, sino, lo crea
        if(item != null){
            //modifica el artículo
            item.descripcion = descripcion;
            item.save();
        }
        else{
            //agrega nuevo articulo
            lista.addArticulo(descripcion);
        }
        lista.save();

    }
    //------------------------------------------------------------------------------------
    public static void guardarListado(Long idLista, String nombre){

        Listado lista = Listado.findById(idLista);

        //verifica que la lista exista, sino, la crea
        if(lista != null){
            //modifica lista
            lista.nombre = nombre;
            lista.feModificacion = new Date();
            lista.save();
        }
        else{
            //agrega nueva lista
            Listado newLista = new Listado(nombre);
            newLista.save();
        }

    }
    //--------------------------------------------------------------------------------------
}