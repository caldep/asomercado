import models.Listado;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Created by caldep on 21/04/15.
 */
@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob()
    {
        if(Listado.count() == 0)
        {
            Fixtures.loadYamlAsMap("data-inicial.yml");
        }
    }
}
