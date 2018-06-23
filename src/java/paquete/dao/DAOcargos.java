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
import paquete.modelo.cargos;

/**
 *
 * @author Elvis
 */
public class DAOcargos {
    
    public ArrayList<cargos> ListarCargos() {
        Connection con = conexion.getConnection();
        ArrayList<cargos> car = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargos c = new cargos();
                c.setId_cargo(rs.getString("id_cargo"));
                c.setNombre_cargo(rs.getString("nombre_cargo"));
                car.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return car;
    }
    
    public cargos BuscarCargo(String id) {
        Connection con = conexion.getConnection();
        cargos c = new cargos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargos WHERE id_cargo = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_cargo(rs.getString("id_cargo"));
                c.setNombre_cargo(rs.getString("nombre_cargo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcargos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public int AgregarCargos(cargos c) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cargos (nombre_cargo) VALUES (?)");
            ps.setString(1, c.getNombre_cargo());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }
    
    public int ActualizarCargos(cargos c) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE cargos SET nombre_cargo = ? WHERE id_cargo = ?");
            ps.setString(1, c.getNombre_cargo());
            ps.setString(2, c.getId_cargo());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }
    
    public int EliminarCargos(String id) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM cargos WHERE id_cargo = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcargos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
