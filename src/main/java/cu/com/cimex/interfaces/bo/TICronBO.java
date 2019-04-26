package cu.com.cimex.interfaces.bo;

import cu.com.cimex.model.TCron;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by enoel on 21/05/18.
 */
public interface TICronBO extends Serializable {

    public void CreaCron(TCron cron) throws Exception;

    public Integer CantidadCrones(Date fecha) throws Exception;

    public List<TCron> ListCrones() throws Exception;

    public void EliminaCron() throws Exception;
}
