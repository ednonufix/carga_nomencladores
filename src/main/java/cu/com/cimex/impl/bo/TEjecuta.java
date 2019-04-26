/*
 *
 *  * Copyright (C) 2018 Eduardo Noel <enoel.corebsd@nauta.cu>
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package cu.com.cimex.impl.bo;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import cu.com.cimex.interfaces.bo.TIEjecuta;
import cu.com.cimex.utiles.Utiles;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Creado el 20/05/18
 */
public class TEjecuta implements Serializable, TIEjecuta {

    final private SimpleLoggerFactory factory = new SimpleLoggerFactory();

    @Override
    public void EjecutaJob() throws Exception {

        final Logger logger = factory.getLogger(this.getClass().getName());

        Connection con = null;
        CallableStatement cstmt = null;

        try {

            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setIntegratedSecurity(false);
            ds.setServerName(Utiles.Singleton().getServidor());
            ds.setPortNumber(Integer.parseInt(Utiles.Singleton().getPuerto()));
            ds.setDatabaseName(Utiles.Singleton().getDatabase());
            ds.setPassword(Utiles.Singleton().getPassword());
            ds.setUser(Utiles.Singleton().getUsuario());

            con = ds.getConnection();

            logger.info("Conexión creada satisfactoriamente");

            cstmt = con.prepareCall("{call dbo.sp_start_job(?)}");
            cstmt.setString(1, Utiles.Singleton().getjob());

            cstmt.execute();

            ResultSet rs = cstmt.getResultSet();

            logger.info("Job mandado a ejecutar satisfactoriamente");

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        } finally {

            if (cstmt != null) try {

                cstmt.close();

            } catch (Exception e) {

                logger.error(e.getMessage());
            }

            if (con != null) try {

                con.close();

            } catch (Exception e) {

                logger.error(e.getMessage());
            }

        }

    }
}
