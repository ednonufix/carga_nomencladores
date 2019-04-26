package cu.com.cimex.utiles;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * Creado a las 8:12 del día 2/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */

public class CompruebaCrones extends QuartzJobBean implements Serializable {

    private int timeout;

    private SimpleLoggerFactory factory = new SimpleLoggerFactory();

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Logger logger = factory.getLogger(this.getClass().getName());

        Integer cant_def = Integer.parseInt(Utiles.Singleton().getCantidad_crones());

        try {

            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
            String restful_services = Utiles.Singleton().getServidor_restful();

            Integer cant = restTemplate.getForObject(restful_services + "/api/cantidad_crones", Integer.class);

            if (cant == cant_def) {

                restTemplate.getForObject(restful_services + "/api/ejecuta_job", Void.class);
                logger.info("Se ha mandado a ejecutar el job en el SQL, se procederá a borrar la lista de crones terminados");

                restTemplate.getForObject(restful_services + "/api/elimina_crones", Void.class);
                logger.info("Lista de crones terminados borrados exitosamente");

            } else {

                logger.info("No se ha ejecutado el job de SQL pues no hay la cantidad requerida de crones terminados");

            }

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }

    }
}
