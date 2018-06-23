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
import paquete.modelo.talles;
import static extras.extras.IsNull;

/**
 *
 * @author Elvis
 */
public class DAOtalles {

    public ArrayList<talles> ListarTalles() {
        Connection con = conexion.getConnection();
        ArrayList<talles> t = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("Select * from talles");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                talles ta = new talles();
                ta.setId_talle(rs.getString("id_talle"));
                ta.setNombre_t(rs.getString("nombre_t"));
                ta.setDescripcion_t(rs.getString("descripcion_t"));
                t.add(ta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOtalles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public talles BuscarTalles(String id) {
        Connection con = conexion.getConnection();
        talles ta = new talles();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM talles WHERE id_talle = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ta.setId_talle(rs.getString("id_talle"));
                ta.setNombre_t(rs.getString("nombre_t"));
                ta.setDescripcion_t(rs.getString("descripcion_t"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOtalles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ta;
    }

    public int AgregarTalles(talles t) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO talles (nombre_t, descripcion_t) VALUES (?, ?)");
            ps.setString(1, t.getNombre_t());
            ps.setString(2, IsNull(t.getDescripcion_t()));
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOtalles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int ActualizarTalles(talles t) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE talles SET nombre_t = ?, descripcion_t = ? WHERE id_talle = ?");
            ps.setString(1, t.getNombre_t());
            ps.setString(2, IsNull(t.getDescripcion_t()));
            ps.setString(3, t.getId_talle());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOtalles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
