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
public class mercaderiasrecibidas extends articulosrecibidos {

    String id_mercaderia_recibida;
    String nrofactura_mr;
    String total_mr;
    String total_iva_mr;
    String fecha_mr;
    String comentario_mr;
    String close_mr;

    public String getId_mercaderia_recibida() {
        return id_mercaderia_recibida;
    }

    public void setId_mercaderia_recibida(String id_mercaderia_recibida) {
        this.id_mercaderia_recibida = id_mercaderia_recibida;
    }

    public String getNrofactura_mr() {
        return nrofactura_mr;
    }

    public void setNrofactura_mr(String nrofactura_mr) {
        this.nrofactura_mr = nrofactura_mr;
    }

    public String getTotal_mr() {
        return total_mr;
    }

    public void setTotal_mr(String total_mr) {
        this.total_mr = total_mr;
    }

    public String getTotal_iva_mr() {
        return total_iva_mr;
    }

    public void setTotal_iva_mr(String total_iva_mr) {
        this.total_iva_mr = total_iva_mr;
    }

    public String getFecha_mr() {
        return fecha_mr;
    }

    public void setFecha_mr(String fecha_mr) {
        this.fecha_mr = fecha_mr;
    }

    public String getComentario_mr() {
        return comentario_mr;
    }

    public void setComentario_mr(String comentario_mr) {
        this.comentario_mr = comentario_mr;
    }

    public String getClose_mr() {
        return close_mr;
    }

    public void setClose_mr(String close_mr) {
        this.close_mr = close_mr;
    }

}
