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
public class empleados extends cargos {

    String id_empleado;
    String nombre_emp;
    String apellido_emp;
    String ndocumento_emp;
    String fecha_ingreso_emp;
    String fnac_emp;
    String estado_emp;
    String cargo_emp;

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getApellido_emp() {
        return apellido_emp;
    }

    public void setApellido_emp(String apellido_emp) {
        this.apellido_emp = apellido_emp;
    }

    public String getNdocumento_emp() {
        return ndocumento_emp;
    }

    public void setNdocumento_emp(String ndocumento_emp) {
        this.ndocumento_emp = ndocumento_emp;
    }

    public String getFecha_ingreso_emp() {
        return fecha_ingreso_emp;
    }

    public void setFecha_ingreso_emp(String fecha_ingreso_emp) {
        this.fecha_ingreso_emp = fecha_ingreso_emp;
    }

    public String getFnac_emp() {
        return fnac_emp;
    }

    public void setFnac_emp(String fnac_emp) {
        this.fnac_emp = fnac_emp;
    }

    public String getEstado_emp() {
        return estado_emp;
    }

    public void setEstado_emp(String estado_emp) {
        this.estado_emp = estado_emp;
    }

    public String getCargo_emp() {
        return cargo_emp;
    }

    public void setCargo_emp(String cargo_emp) {
        this.cargo_emp = cargo_emp;
    }
}
