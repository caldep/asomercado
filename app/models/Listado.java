package models;

import play.data.validation.*;
import play.db.jpa.*;
import java.util.*;
import javax.persistence.*;

/**
 * Created by caldep on 20/04/15.
 */

@Entity
public class Listado extends Model{

    @Required
    public String   nombre;
    @Required
    public Date     feCreacion;
    @Required
    public Date     feModificacion;
    //public Boolean  isActivo;

    @OneToMany(mappedBy="lista", cascade=CascadeType.ALL, orphanRemoval = true)
    public List<Articulo> articulos;

    //-----------------------------------------------------
    public Listado(String nombre)
    {
        this.nombre         =   nombre;
        this.feCreacion     =   new Date();
        this.feModificacion =   this.feCreacion;
        //this.isActivo       =   true;
        this.articulos      =   new ArrayList<Articulo>();
    }
    //-----------------------------------------------------
    public Listado addArticulo(String descripcion) {
        Articulo newArticulo = new Articulo(this, descripcion).save();
        this.articulos.add(newArticulo);
        this.feModificacion = new Date();
        this.save();
        return this;
    }
    //------------------------------------------------------
    public Listado siguiente() {
        return Listado.find("id > ? ", id).first();
    }
    //------------------------------------------------------
    public Listado anterior() {
        return Listado.find("id < ?", id).first();
    }
    //------------------------------------------------------
    public void eliminar(){

        for(int i=0; i < this.articulos.size(); i++) {
            this.articulos.get(i).delete();
        }
        this.articulos.clear();
        this.refresh();
        this.delete();
    }
    //-------------------------------------------------------------------------

}
