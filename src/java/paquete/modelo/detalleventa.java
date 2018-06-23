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
public class detalleventa extends clientes {

    String talle_ven;
    String cantidad_ven;
    String precio_ven;
    String iva;
    String subtotal_ven;

    public String getTalle_ven() {
        return talle_ven;
    }

    public void setTalle_ven(String talle_ven) {
        this.talle_ven = talle_ven;
    }

    public String getCantidad_ven() {
        return cantidad_ven;
    }

    public void setCantidad_ven(String cantidad_ven) {
        this.cantidad_ven = cantidad_ven;
    }

    public String getPrecio_ven() {
        return precio_ven;
    }

    public void setPrecio_ven(String precio_ven) {
        this.precio_ven = precio_ven;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getSubtotal_ven() {
        return subtotal_ven;
    }

    public void setSubtotal_ven(String subtotal_ven) {
        this.subtotal_ven = subtotal_ven;
    }

}
