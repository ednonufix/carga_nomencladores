<%--
  ~ /*
  ~  * Copyright (C) 2018 Eduardo Noel <enoel.corebsd@nauta.cu>
  ~  *
  ~  * This program is free software: you can redistribute it and/or modify
  ~  * it under the terms of the GNU General Public License as published by
  ~  * the Free Software Foundation, either version 3 of the License, or
  ~  * (at your option) any later version.
  ~  *
  ~  * This program is distributed in the hope that it will be useful,
  ~  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~  * GNU General Public License for more details.
  ~  *
  ~  * You should have received a copy of the GNU General Public License
  ~  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  ~  */
  --%>

<%--
  User: Eduardo Noel Núñez <enoel.corebsd@gmail.com>  
  Date: 9/02/17
  Time: 18:39  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Repositorios de Servicios Web</title>
</head>

<body>

El sitio esta listo para servir

<br/>

/api/ejecuta_job -- manda a ejecutar el job en el SQL

<br/>

/api/cantidad_crones -- muestra la cantidad de crones ejecutados

<br/>

/api/lista_crones -- muestra la lista donde los crones se han ejecutado

<br/>

/api/crea_cron -- Este es el servicio web que usan los clientes para notificar su culminación

<br/>

/api/elimina_crones -- Elimina todos los crones ejecutados para no volver a llamar al job SQL

</body>

</html>

