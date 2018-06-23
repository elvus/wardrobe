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
public class recibos extends ventas_has_recibos {

    String id_recibo;
    String fecha_rec;
    String monto_rec;
    String comentario_rec;
    String close_rec;

    public String getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(String id_recibo) {
        this.id_recibo = id_recibo;
    }

    public String getFecha_rec() {
        return fecha_rec;
    }

    public void setFecha_rec(String fecha_rec) {
        this.fecha_rec = fecha_rec;
    }

    public String getMonto_rec() {
        return monto_rec;
    }

    public void setMonto_rec(String monto_rec) {
        this.monto_rec = monto_rec;
    }

    public String getComentario_rec() {
        return comentario_rec;
    }

    public void setComentario_rec(String comentario_rec) {
        this.comentario_rec = comentario_rec;
    }

    public String getClose_rec() {
        return close_rec;
    }

    public void setClose_rec(String close_rec) {
        this.close_rec = close_rec;
    }

}
