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

package cu.com.cimex.restful;


import cu.com.cimex.interfaces.bo.TICronBO;
import cu.com.cimex.interfaces.bo.TIEjecuta;
import cu.com.cimex.model.TCron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Creado a las 18:27 del día 24/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
@RestController
public class RestServices implements Serializable {

    private static final long serialVersionUID = -856072L;

    @Autowired
    TIEjecuta ejecutaBO;

    @Autowired
    TICronBO cronBO;

    /**
     * Ejecuta comando Linux declarado en el fichero config.properties
     *
     * @throws Exception
     */
    @GetMapping(path = "/api/ejecuta_job")
    public void ejecuta_cmd() throws Exception {

        ejecutaBO.EjecutaJob();

    }

    @GetMapping(path = "/api/cantidad_crones")
    public Integer cantidad_crones() throws Exception {

        Calendar fecha = new GregorianCalendar();

        return cronBO.CantidadCrones(fecha.getTime());

    }

    @GetMapping(path = "/api/lista_crones")
    public ResponseEntity<List> lista_crones() throws Exception {

        return new ResponseEntity<List>(cronBO.ListCrones(), HttpStatus.OK);

    }

    @PostMapping(path = "/api/crea_cron", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void AnnadeCron(@RequestBody TCron cron) throws Exception {

        cronBO.CreaCron(cron);

    }

    @GetMapping(path = "/api/elimina_crones")
    public void elimina_crones() throws Exception {

        cronBO.EliminaCron();

    }


}
