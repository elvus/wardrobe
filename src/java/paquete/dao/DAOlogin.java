/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import static extras.Hash.md5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.usuarios;

/**
 *
 * @author Elvis
 */
public class DAOlogin {

    public String getUsuario(String user, String pass) {
        Connection connection = conexion.getConnection();
        String r = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios where alias = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, md5(pass));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = rs.getString("id_usuario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOlogin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

}
