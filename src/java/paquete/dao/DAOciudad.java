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
import paquete.modelo.ciudades;

/**
 *
 * @author Elvis
 */
public class DAOciudad {

    public ArrayList<ciudades> ListarCiudades() {
        Connection connection = conexion.getConnection();
        ArrayList<ciudades> city = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ciudades c INNER JOIN departamentos d ON c.id_departamento = d.id_departamento");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ciudades c = new ciudades();
                c.setId_ciudad(rs.getString("id_ciudad"));
                c.setNombre_ciudad(rs.getString("nombre_ciudad"));
                c.setNombre_dep(rs.getString("nombre_dep"));
                city.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOciudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return city;
    }

    public ciudades BuscarCiudades(String id) {
        Connection connection = conexion.getConnection();
        ciudades c = new ciudades();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ciudades where id_ciudad = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_ciudad(rs.getString("id_ciudad"));
                c.setNombre_ciudad(rs.getString("nombre_ciudad"));
                c.setId_departamento(rs.getString("id_departamento"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOciudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return c;
    }

    public int AgregarCiudad(ciudades c) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ciudades(nombre_ciudad, id_departamento) VALUES (?, ?)");
            ps.setString(1, c.getNombre_ciudad());
            ps.setString(2, c.getId_departamento());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOciudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarCiudad(ciudades c) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ciudades SET nombre_ciudad = ?, id_departamento = ? WHERE id_ciudad = ?");
            ps.setString(1, c.getNombre_ciudad());
            ps.setString(2, c.getId_departamento());
            ps.setString(3, c.getId_ciudad());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOciudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int EliminarCiudad(String id) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ciudades WHERE id_ciudad = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOciudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }
}
