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
public class ventas_has_recibos extends ventas {

    String subtotal_rec;
    String concepto_rec;

    public String getSubtotal_rec() {
        return subtotal_rec;
    }

    public void setSubtotal_rec(String subtotal_rec) {
        this.subtotal_rec = subtotal_rec;
    }

    public String getConcepto_rec() {
        return concepto_rec;
    }

    public void setConcepto_rec(String concepto_rec) {
        this.concepto_rec = concepto_rec;
    }

}
