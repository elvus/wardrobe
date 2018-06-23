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
public class ventas extends detalleventa {

    String id_venta;
    String factura_ven;
    String tipo_ven;
    String aprobado_ven;
    String fecha_ven;
    String total_ven;
    String total_iva_ven;
    String close_ven;
    String nro_factura;

    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public String getFactura_ven() {
        return factura_ven;
    }

    public void setFactura_ven(String factura_ven) {
        this.factura_ven = factura_ven;
    }

    public String getTipo_ven() {
        return tipo_ven;
    }

    public void setTipo_ven(String tipo_ven) {
        this.tipo_ven = tipo_ven;
    }

    public String getAprobado_ven() {
        return aprobado_ven;
    }

    public void setAprobado_ven(String aprobado_ven) {
        this.aprobado_ven = aprobado_ven;
    }

    public String getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(String fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getTotal_ven() {
        return total_ven;
    }

    public void setTotal_ven(String total_ven) {
        this.total_ven = total_ven;
    }

    public String getTotal_iva_ven() {
        return total_iva_ven;
    }

    public void setTotal_iva_ven(String total_iva_ven) {
        this.total_iva_ven = total_iva_ven;
    }

    public String getClose_ven() {
        return close_ven;
    }

    public void setClose_ven(String close_ven) {
        this.close_ven = close_ven;
    }

    public String getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(String nro_factura) {
        this.nro_factura = nro_factura;
    }

}
