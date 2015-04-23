package models;

import org.hibernate.annotations.Type;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by caldep on 20/04/15.
 */

@Entity
public class Articulo extends Model{

    public String descripcion;

    @ManyToOne
    public Listado lista;

    public Boolean isPendiente;

    //-----------------------------------------------------
    public Articulo(Listado lista, String descripcion )
    {
        this.lista          =   lista;
        this.descripcion    =   descripcion;
        this.isPendiente    =   true;
    }
    //-----------------------------------------------------

}
