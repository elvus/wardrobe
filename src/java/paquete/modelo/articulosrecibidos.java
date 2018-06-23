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
public class articulosrecibidos extends articulos {

    String cantidad_rec;
    String talle_mr;
    String precio_unitario_rec;
    String subtotal_rec;
    String iva_rec;

    public String getCantidad_rec() {
        return cantidad_rec;
    }

    public void setCantidad_rec(String cantidad_rec) {
        this.cantidad_rec = cantidad_rec;
    }

    public String getTalle_mr() {
        return talle_mr;
    }

    public void setTalle_mr(String talle_mr) {
        this.talle_mr = talle_mr;
    }

    public String getPrecio_unitario_rec() {
        return precio_unitario_rec;
    }

    public void setPrecio_unitario_rec(String precio_unitario_rec) {
        this.precio_unitario_rec = precio_unitario_rec;
    }

    public String getSubtotal_rec() {
        return subtotal_rec;
    }

    public void setSubtotal_rec(String subtotal_rec) {
        this.subtotal_rec = subtotal_rec;
    }

    public String getIva_rec() {
        return iva_rec;
    }

    public void setIva_rec(String iva_rec) {
        this.iva_rec = iva_rec;
    }

}
