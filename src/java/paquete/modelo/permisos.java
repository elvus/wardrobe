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
public class permisos extends usuarios_has_permisos {

    String id_permiso;
    String modulo_per;

    public String getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(String id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getModulo_per() {
        return modulo_per;
    }

    public void setModulo_per(String modulo_per) {
        this.modulo_per = modulo_per;
    }

}
