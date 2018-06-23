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
import paquete.modelo.colores;

/**
 *
 * @author Elvis
 */
public class DAOcolor {

    public int AgregarColor(colores col) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO colores (nombre_color) VALUES (?)");
            ps.setString(1, col.getNombre_color());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcolor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarColor(colores col) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE colores SET nombre_color=? where id_color=?");
            ps.setString(1, col.getNombre_color());
            ps.setString(2, col.getId_color());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcolor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public ArrayList<colores> ListarColores() {
        Connection connection = conexion.getConnection();
        ArrayList<colores> col = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM colores");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                colores c = new colores();
                c.setId_color(rs.getString("id_color"));
                c.setNombre_color(rs.getString("nombre_color"));
                col.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcolor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return col;
    }

    public colores BuscarColores(String id) {
        Connection connection = conexion.getConnection();
        colores c = new colores();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM colores WHERE id_color = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_color(rs.getString("id_color"));
                c.setNombre_color(rs.getString("nombre_color"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcolor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return c;
    }

    public int EliminarColores(String id) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM colores where id_color=?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcolor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }
}
