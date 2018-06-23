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
public class grupos extends marcas {

    String id_talle;
    String nombre_t;
    String abreviatura;

    public String getId_talle() {
        return id_talle;
    }

    public void setId_talle(String id_talle) {
        this.id_talle = id_talle;
    }

    public String getNombre_t() {
        return nombre_t;
    }

    public void setNombre_t(String nombre_t) {
        this.nombre_t = nombre_t;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

}
