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
public class auditoria extends empleados {

    String id_auditoria;
    String id_auditado;
    String usuario;
    String tabla;
    String evento;
    String descripcion;
    String fecha;

    public String getId_auditoria() {
        return id_auditoria;
    }

    public void setId_auditoria(String id_auditoria) {
        this.id_auditoria = id_auditoria;
    }

    public String getId_auditado() {
        return id_auditado;
    }

    public void setId_auditado(String id_auditado) {
        this.id_auditado = id_auditado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
