package cu.com.cimex.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by enoel on 21/05/18.
 */
public class TCron implements Serializable {

    private Long id_cron;
    private String nomb_cron;
    private Date fecha_culminado;

    public Long getId_cron() {
        return id_cron;
    }

    public void setId_cron(Long id_cron) {
        this.id_cron = id_cron;
    }

    public String getNomb_cron() {
        return nomb_cron;
    }

    public void setNomb_cron(String nomb_cron) {
        this.nomb_cron = nomb_cron;
    }

    public Date getFecha_culminado() {
        return fecha_culminado;
    }

    public void setFecha_culminado(Date fecha_culminado) {
        this.fecha_culminado = fecha_culminado;
    }
}
