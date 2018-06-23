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
import paquete.modelo.marcas;

/**
 *
 * @author Elvis
 */
public class DAOmarcas {

    public ArrayList<marcas> ListarMarcas() {
        ArrayList<marcas> mar = new ArrayList<>();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM marcas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marcas m = new marcas();
                m.setId_marca(rs.getString("id_marca"));
                m.setNombre_marca(rs.getString("nombre_marca"));
                mar.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return mar;
    }

    public marcas BuscarMarcas(String id) {
        Connection connection = conexion.getConnection();
        marcas mar = new marcas();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM marcas WHERE id_marca = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mar.setId_marca(rs.getString("id_marca"));
                mar.setNombre_marca(rs.getString("nombre_marca"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return mar;
    }

    public int AgregarMarcas(marcas m) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO marcas(nombre_marca) VALUES (?)");
            ps.setString(1, m.getNombre_marca());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarMarcas(marcas m) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE marcas SET nombre_marca = ? WHERE id_marca = ?");
            ps.setString(1, m.getNombre_marca());
            ps.setString(2, m.getId_marca());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int EliminarMarca(String id) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM marcas WHERE id_marca = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }
}
