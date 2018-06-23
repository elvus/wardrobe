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
public class gastos_has_articulos extends articulos {

    String concepto_gasto;
    String monto_gasto;

    public String getConcepto_gasto() {
        return concepto_gasto;
    }

    public void setConcepto_gasto(String concepto_gasto) {
        this.concepto_gasto = concepto_gasto;
    }

    public String getMonto_gasto() {
        return monto_gasto;
    }

    public void setMonto_gasto(String monto_gasto) {
        this.monto_gasto = monto_gasto;
    }

}
