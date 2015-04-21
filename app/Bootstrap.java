import models.*;
import play.jobs.*;
import play.test.*;

/**
 * Created by caldep on 21/04/15.
 */
@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob()
    {
        if(Listado.count() == 0)
        {
            Fixtures.deleteAll();
            Fixtures.load("data-inicial.yml");
        }
    }
}
