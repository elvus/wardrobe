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
public class gastos extends gastos_has_articulos {

    String id_gastos;
    String total_gasto;
    String fecha_gasto;

    public String getId_gastos() {
        return id_gastos;
    }

    public void setId_gastos(String id_gastos) {
        this.id_gastos = id_gastos;
    }

    public String getTotal_gasto() {
        return total_gasto;
    }

    public void setTotal_gasto(String total_gasto) {
        this.total_gasto = total_gasto;
    }

    public String getFecha_gasto() {
        return fecha_gasto;
    }

    public void setFecha_gasto(String fecha_gasto) {
        this.fecha_gasto = fecha_gasto;
    }

}
