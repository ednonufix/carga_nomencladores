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

package cu.com.cimex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

/**
 * Creado el 11/03/18
 */
@Controller(value = "indexController")
public class IndexController implements Serializable {

    @RequestMapping(method = RequestMethod.GET, value = "/inicio")
    public ModelAndView index() throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        return mav;
    }

}
