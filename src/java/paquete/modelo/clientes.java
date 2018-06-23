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
public class clientes extends articulos {

    String id_cliente;
    String nombre_cli;
    String apellido_cli;
    String documento_cli;
    String dv_cli;
    String telefono_cli;
    String correo_cli;
    String celular_cli;
    String direccion_cli;

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public String getApellido_cli() {
        return apellido_cli;
    }

    public void setApellido_cli(String apellido_cli) {
        this.apellido_cli = apellido_cli;
    }

    public String getDocumento_cli() {
        return documento_cli;
    }

    public void setDocumento_cli(String documento_cli) {
        this.documento_cli = documento_cli;
    }

    public String getDv_cli() {
        return dv_cli;
    }

    public void setDv_cli(String dv_cli) {
        this.dv_cli = dv_cli;
    }

    public String getTelefono_cli() {
        return telefono_cli;
    }

    public void setTelefono_cli(String telefono_cli) {
        this.telefono_cli = telefono_cli;
    }

    public String getCorreo_cli() {
        return correo_cli;
    }

    public void setCorreo_cli(String correo_cli) {
        this.correo_cli = correo_cli;
    }

    public String getCelular_cli() {
        return celular_cli;
    }

    public void setCelular_cli(String celular_cli) {
        this.celular_cli = celular_cli;
    }

    public String getDireccion_cli() {
        return direccion_cli;
    }

    public void setDireccion_cli(String direccion_cli) {
        this.direccion_cli = direccion_cli;
    }
}
