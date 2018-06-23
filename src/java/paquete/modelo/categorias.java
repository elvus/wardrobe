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
public class categorias extends proveedores {

    String id_categoria;
    String nombre_cat;
    String descripcion_cat;

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_cat() {
        return nombre_cat;
    }

    public void setNombre_cat(String nombre_cat) {
        this.nombre_cat = nombre_cat;
    }

    public String getDescripcion_cat() {
        return descripcion_cat;
    }

    public void setDescripcion_cat(String descripcion_cat) {
        this.descripcion_cat = descripcion_cat;
    }

}
