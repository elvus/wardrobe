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
public class caja {

    String id_caja;
    String saldo_anterior;
    String saldo_disponible;
    String fecha_caja;
    String ingreso_caja;
    String egreso_caja;

    public String getId_caja() {
        return id_caja;
    }

    public void setId_caja(String id_caja) {
        this.id_caja = id_caja;
    }

    public String getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(String saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    public String getSaldo_disponible() {
        return saldo_disponible;
    }

    public void setSaldo_disponible(String saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }

    public String getFecha_caja() {
        return fecha_caja;
    }

    public void setFecha_caja(String fecha_caja) {
        this.fecha_caja = fecha_caja;
    }

    public String getIngreso_caja() {
        return ingreso_caja;
    }

    public void setIngreso_caja(String ingreso_caja) {
        this.ingreso_caja = ingreso_caja;
    }

    public String getEgreso_caja() {
        return egreso_caja;
    }

    public void setEgreso_caja(String egreso_caja) {
        this.egreso_caja = egreso_caja;
    }

}
