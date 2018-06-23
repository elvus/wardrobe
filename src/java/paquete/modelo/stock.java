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
public class stock extends articulos {

    String id_stock;
    String cantidad;

    public String getId_stock() {
        return id_stock;
    }

    public void setId_stock(String id_stock) {
        this.id_stock = id_stock;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
