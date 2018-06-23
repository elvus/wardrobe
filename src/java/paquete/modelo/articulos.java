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
public class articulos extends grupos {

    String id_articulo;
    String codigo_ar;
    String barcode_ar;
    String precio_ar;
    String precio_compra_ar;
    String descripcion_ar;
    String iva_ar;
    String temporada_ar;
    String talle_ar;
    String close_ar;

    public String getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(String id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getCodigo_ar() {
        return codigo_ar;
    }

    public void setCodigo_ar(String codigo_ar) {
        this.codigo_ar = codigo_ar;
    }

    public String getBarcode_ar() {
        return barcode_ar;
    }

    public void setBarcode_ar(String barcode_ar) {
        this.barcode_ar = barcode_ar;
    }

    public String getPrecio_ar() {
        return precio_ar;
    }

    public void setPrecio_ar(String precio_ar) {
        this.precio_ar = precio_ar;
    }

    public String getPrecio_compra_ar() {
        return precio_compra_ar;
    }

    public void setPrecio_compra_ar(String precio_compra_ar) {
        this.precio_compra_ar = precio_compra_ar;
    }

    public String getDescripcion_ar() {
        return descripcion_ar;
    }

    public void setDescripcion_ar(String descripcion_ar) {
        this.descripcion_ar = descripcion_ar;
    }

    public String getIva_ar() {
        return iva_ar;
    }

    public void setIva_ar(String iva_ar) {
        this.iva_ar = iva_ar;
    }

    public String getTemporada_ar() {
        return temporada_ar;
    }

    public void setTemporada_ar(String temporada_ar) {
        this.temporada_ar = temporada_ar;
    }

    public String getTalle_ar() {
        return talle_ar;
    }

    public void setTalle_ar(String talle_ar) {
        this.talle_ar = talle_ar;
    }

    public String getClose_ar() {
        return close_ar;
    }

    public void setClose_ar(String close_ar) {
        this.close_ar = close_ar;
    }

}
