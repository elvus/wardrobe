/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.permisos;

/**
 *
 * @author Elvis
 */
public class DAOpermisos {

    public ArrayList<permisos> ListarPermisos() {
        Connection con = conexion.getConnection();
        ArrayList<permisos> per = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM permisos");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                permisos p = new permisos();
                p.setId_permiso(rs.getString("id_permiso"));
                p.setModulo_per(rs.getString("modulo_per"));
                per.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOpermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return per;
    }

    public ArrayList<permisos> BuscarPermisosxUsuarios(String id_usuario) {
        Connection con = conexion.getConnection();
        ArrayList<permisos> per = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios_has_permisos WHERE id_usuario = ?");
            ps.setString(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                permisos p = new permisos();
            }                
        } catch (SQLException ex) {
            Logger.getLogger(DAOpermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return per;
    }
}
