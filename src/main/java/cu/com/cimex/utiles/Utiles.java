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

package cu.com.cimex.utiles;

import java.io.Serializable;

/**
 * Creado a las 19:40 del día 16/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
public class Utiles implements Serializable {

    private String job, servidor, database, puerto, usuario, password, cantidad_crones, servidor_restful, ftp, usuario_ftp, password_ftp, ruta_ftp;

    public static Utiles Singleton() {

        return CargaPropertiesHolder.INSTANCE;
    }

    private static class CargaPropertiesHolder {

        private static final Utiles INSTANCE = new Utiles();
    }

    public String getjob() {
        return job;
    }

    public void setjob(String job) {
        this.job = job;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCantidad_crones() {
        return cantidad_crones;
    }

    public String getServidor_restful() {
        return servidor_restful;
    }

    public void setServidor_restful(String servidor_restful) {
        this.servidor_restful = servidor_restful;
    }

    public void setCantidad_crones(String cantidad_crones) {
        this.cantidad_crones = cantidad_crones;
    }

    public String getFtp() {
        return ftp;
    }

    public void setFtp(String ftp) {
        this.ftp = ftp;
    }

    public String getUsuario_ftp() {
        return usuario_ftp;
    }

    public void setUsuario_ftp(String usuario_ftp) {
        this.usuario_ftp = usuario_ftp;
    }

    public String getPassword_ftp() {
        return password_ftp;
    }

    public void setPassword_ftp(String password_ftp) {
        this.password_ftp = password_ftp;
    }

    public String getRuta_ftp() {
        return ruta_ftp;
    }

    public void setRuta_ftp(String ruta_ftp) {
        this.ruta_ftp = ruta_ftp;
    }
}
