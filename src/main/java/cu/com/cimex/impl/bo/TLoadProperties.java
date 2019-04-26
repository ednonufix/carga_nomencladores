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


import cu.com.cimex.interfaces.bo.TILoadProperties;
import cu.com.cimex.utiles.Utiles;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.Properties;

/**
 * Creado a las 16:10 del día 4/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class TLoadProperties implements Serializable, TILoadProperties {

    @Autowired
    ServletContext context;

    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    final private SimpleLoggerFactory factory = new SimpleLoggerFactory();

    @Autowired
    @Override
    public void loadProperties() throws Exception {

        final Logger logger = factory.getLogger(this.getClass().getName());

        Properties prop = new Properties();

        try {

            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            Utiles.Singleton().setServidor(prop.getProperty("servidor"));
            Utiles.Singleton().setDatabase(prop.getProperty("database"));
            Utiles.Singleton().setPuerto(prop.getProperty("puerto"));
            Utiles.Singleton().setUsuario(prop.getProperty("usuario"));
            Utiles.Singleton().setPassword(prop.getProperty("password"));
            Utiles.Singleton().setjob(prop.getProperty("job"));
            Utiles.Singleton().setCantidad_crones(prop.getProperty("cantidad_crones"));
            Utiles.Singleton().setServidor_restful(prop.getProperty("servidor_restful"));
            Utiles.Singleton().setFtp(prop.getProperty("ftp"));
            Utiles.Singleton().setUsuario_ftp(prop.getProperty("usuario_ftp"));
            Utiles.Singleton().setPassword_ftp(prop.getProperty("password_ftp"));
            Utiles.Singleton().setRuta_ftp(prop.getProperty("ruta_ftp"));

            logger.info("Propiedades cargadas satisfactoriamente");

        } catch (Exception ex) {

            logger.error(ex.getMessage());

        }
    }
}
