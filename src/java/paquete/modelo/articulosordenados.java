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
public class articulosordenados extends articulos {

    String precio_ord;
    String cantidad_ord;
    String iva_ord;
    String codigo_ar_ord;

    public String getPrecio_ord() {
        return precio_ord;
    }

    public void setPrecio_ord(String precio_ord) {
        this.precio_ord = precio_ord;
    }

    public String getCantidad_ord() {
        return cantidad_ord;
    }

    public void setCantidad_ord(String cantidad_ord) {
        this.cantidad_ord = cantidad_ord;
    }

    public String getIva_ord() {
        return iva_ord;
    }

    public void setIva_ord(String iva_ord) {
        this.iva_ord = iva_ord;
    }

    public String getCodigo_ar_ord() {
        return codigo_ar_ord;
    }

    public void setCodigo_ar_ord(String codigo_ar_ord) {
        this.codigo_ar_ord = codigo_ar_ord;
    }

}
