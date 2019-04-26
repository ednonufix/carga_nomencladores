package cu.com.cimex.utiles;

import com.enterprisedt.net.ftp.*;
import cu.com.cimex.model.TCron;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by enoel on 27/05/18.
 */
public class ProcesaFTP extends QuartzJobBean implements Serializable {

    private int timeout;

    FileTransferClient ftp = new FileTransferClient();

    private SimpleLoggerFactory factory = new SimpleLoggerFactory();

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Logger logger = factory.getLogger(this.getClass().getName());

        try {

            String ruta = Utiles.Singleton().getRuta_ftp();

            connectFTP();

            compruebaCrones(ruta);

            disconnectFTP();

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }

    }

    public void connectFTP() throws Exception {

        Logger logger = factory.getLogger(this.getClass().getName());

        try {

            ftp.setRemoteHost(Utiles.Singleton().getFtp());
            ftp.setUserName(Utiles.Singleton().getUsuario_ftp());
            ftp.setPassword(Utiles.Singleton().getPassword_ftp());

            ftp.setContentType(FTPTransferType.ASCII);
            ftp.getAdvancedFTPSettings().setConnectMode(FTPConnectMode.PASV);

            ftp.connect();

            logger.info("Conectado al FTP satisfactoriamente");

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }


    }


    public void disconnectFTP() throws Exception {

        Logger logger = factory.getLogger(this.getClass().getName());

        try {

            ftp.disconnect();

            logger.info("Se ha desconectado del servidor FTP");

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }

    }


    public void compruebaCrones(String ruta) throws Exception {

        Logger logger = factory.getLogger(this.getClass().getName());

        try {

            ftp.changeDirectory(ruta);

            logger.info("Estoy trabajando en la ruta :"+ruta);

            FTPFile[] files = ftp.directoryList();

            for (FTPFile valor : files) {

                String temp = valor.getName();

                String nombre = eliminaEspacios(temp);

                if (!valor.isDir() && !nombre.startsWith(".")) {

                    Calendar fecha = new GregorianCalendar();

                    // Descargo el fichero para un stream, esto es como una especie de buffer en la materia
                    FileTransferInputStream fis = ftp.downloadStream(nombre);

                    // Extraigo el contenido
                    String str = IOUtils.toString(fis, "UTF8");

                    fis.close();

                    if (str.compareTo("PROCESADO") >= 0) {

                        // Creo el cron para enviar al servicio web
                        TCron cron = new TCron();

                        cron.setFecha_culminado(fecha.getTime());
                        cron.setNomb_cron(nombre);

                        RestTemplate restTemplate = new RestTemplate();

                        // Lo notifico
                        restTemplate.postForObject(Utiles.Singleton().getServidor_restful() + "/api/crea_cron", cron, Void.class);

                        // Borro el fichero que acaba de ser procesado del FTP
                        ftp.deleteFile(nombre);

                        // Preparo el contenido que va a tener el fichero a subir
                        InputStream to_store = IOUtils.toInputStream("ESPERANDO", "UTF8");

                        // Creo un nuevo fichero con el nombre descargado
                        File tempFile = new File(nombre);

                        // Guardo el contenido del stream en el fichero
                        FileUtils.copyInputStreamToFile(to_store,tempFile);

                        // Subo el fichero con el mismo nombre que lo descargue y el contenido definido
                        ftp.uploadFile(tempFile.getName(), nombre);

                        tempFile.delete();

                        logger.info("Se ha procesado correctamente el fichero: " + nombre);

                    }

                }

            }


        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }

    }

    private String eliminaEspacios(String valor) {

            int pos_white = valor.lastIndexOf(" ");

            String temp = valor.substring(pos_white + 1);

        return temp;

    }

}
