package models;

import play.db.jpa.*;
import java.util.*;
import javax.persistence.*;

/**
 * Created by caldep on 20/04/15.
 */

@Entity
public class Listado extends Model{

    public String   nombre;
    public Date     feCreacion;
    public Date     feModificacion;
    public Boolean  isActivo;

    @OneToMany(mappedBy="lista", cascade=CascadeType.ALL, orphanRemoval = true)
    public List<Articulo> articulos;

    //-----------------------------------------------------
    public Listado(String nombre)
    {
        this.nombre         =   nombre;
        this.feCreacion     =   new Date();
        this.feModificacion =   this.feCreacion;
        this.isActivo       =   true;
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

}
