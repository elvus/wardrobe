/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.modelo;

/**
 *
 * @author Elvis
 */
public class usuarios_has_permisos {

    String usuarios_id_usuario;
    String permisos_id_permiso;
    String ver;
    String agregar;
    String editar;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getAgregar() {
        return agregar;
    }

    public void setAgregar(String agregar) {
        this.agregar = agregar;
    }

    public String getEditar() {
        return editar;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

}
