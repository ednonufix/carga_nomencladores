package cu.com.cimex.impl.bo;


import cu.com.cimex.interfaces.bo.TICronBO;
import cu.com.cimex.interfaces.dao.TICronDAO;
import cu.com.cimex.model.TCron;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by enoel on 21/05/18.
 */
public class TCronBOImpl implements TICronBO, Serializable {

    @Autowired
    TICronDAO cronDAO;

    @Override
    public void CreaCron(TCron cron) throws Exception {

        cronDAO.CreaCron(cron);

    }

    @Override
    public Integer CantidadCrones(Date fecha) throws Exception {

        return cronDAO.CantidadCrones(fecha);
    }

    @Override
    public List<TCron> ListCrones() throws Exception {

        return cronDAO.ListCrones();
    }

    @Override
    public void EliminaCron() throws Exception {

        cronDAO.EliminaCron();
    }
}
