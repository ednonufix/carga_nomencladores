package cu.com.cimex.interfaces.dao;

import cu.com.cimex.model.TCron;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by enoel on 21/05/18.
 */
public interface TICronDAO extends Serializable {

    public void CreaCron(TCron cron) throws DataAccessException;

    public Integer CantidadCrones(Date fecha) throws DataAccessException;

    public List<TCron> ListCrones() throws DataAccessException;

    public void EliminaCron() throws Exception;
}
