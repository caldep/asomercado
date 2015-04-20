package models;

import org.hibernate.annotations.Type;
import play.db.jpa.*;
import java.util.*;
import javax.persistence.*;

/**
 * Created by caldep on 20/04/15.
 */

@Entity
public class Listado extends Model{

    public String   nombre;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String   contenido;

    public Date     feCreacion;
    public Date     feModifcacion;
    public Boolean  isActivo;

    //-----------------------------------------------------
    public Listado(String nombre, String contenido)
    {
        this.nombre         =   nombre;
        this.contenido      =   contenido;
        this.feCreacion     =   new Date();
        this.feModifcacion  =   new Date();
        this.isActivo       =   true;
    }
    //-----------------------------------------------------

}
