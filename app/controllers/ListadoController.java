package controllers;

import play.mvc.Controller;
import models.*;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by caldep on 23/04/15.
 */
public class ListadoController extends Controller {

    //--------------------------------------------------------------------------------
    public static void show(Long id){
        Listado listaMostrar = Listado.findById(id);
        render(listaMostrar);
    }
    //--------------------------------------------------------------------------------
    public static Long guardarArticulo(Long idArticulo, String descripcion, Long idLista){

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
            idArticulo = lista.articulos.get(lista.articulos.size()-1).getId();
        }
        lista.save();
        return idArticulo;
    }
    //------------------------------------------------------------------------------------
    public static Long guardarListado(Long idLista, String nombre){

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
            idLista = newLista.getId();
        }
        return idLista;
    }
    //--------------------------------------------------------------------------------------
    public static void eliminarListado(Long idListado){
        Listado lista = Listado.findById(idListado);
        lista.eliminar();
    }
    //--------------------------------------------------------------------------------------
    public static void eliminarArticulo(Long idArticulo){
        Articulo item = Articulo.findById(idArticulo);
        item.delete();
    }
    //--------------------------------------------------------------------------------------
    public static void nuevaLista(){
        render();
    }
}
