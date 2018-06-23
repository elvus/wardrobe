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
public class contactos extends ciudades {

    String id_contacto;
    String telefono_cont;
    String correo_cont;
    String celular_cont;
    String principal_cont;

    public String getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(String id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getTelefono_cont() {
        return telefono_cont;
    }

    public void setTelefono_cont(String telefono_cont) {
        this.telefono_cont = telefono_cont;
    }

    public String getCorreo_cont() {
        return correo_cont;
    }

    public void setCorreo_cont(String correo_cont) {
        this.correo_cont = correo_cont;
    }

    public String getCelular_cont() {
        return celular_cont;
    }

    public void setCelular_cont(String celular_cont) {
        this.celular_cont = celular_cont;
    }

    public String getPrincipal_cont() {
        return principal_cont;
    }

    public void setPrincipal_cont(String principal_cont) {
        this.principal_cont = principal_cont;
    }

    
}
