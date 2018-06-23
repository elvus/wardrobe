/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import static extras.extras.IsNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.categorias;

/**
 *
 * @author Elvis
 */
public class DAOcategoria {

    public ArrayList<categorias> ListarCategorias() {
        Connection con = conexion.getConnection();
        ArrayList<categorias> c = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorias cat = new categorias();
                cat.setId_categoria(rs.getString("id_categoria"));
                cat.setNombre_cat(rs.getString("nombre_cat"));
                cat.setDescripcion_cat(rs.getString("descripcion_cat"));
                c.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcategoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return c;
    }

    public categorias buscarcategorias(String id_cat) {
        Connection con = conexion.getConnection();
        categorias cat = new categorias();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ?");
            ps.setString(1, id_cat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cat.setNombre_cat(rs.getString("nombre_cat"));
                cat.setDescripcion_cat(rs.getString("descripcion_cat"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcategoria.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return cat;
    }

    public int AgregarCategorias(categorias cat) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO categoria (nombre_cat, descripcion_cat) VALUES (?, ?)");
            ps.setString(1, cat.getNombre_cat());
            ps.setString(2, IsNull(cat.getDescripcion_cat()));
            r = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOcategoria.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int ActualizarCategorias(categorias cat) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE categoria SET nombre_cat = ?, descripcion_cat = ? WHERE id_categoria = ?");
            ps.setString(1, cat.getNombre_cat());
            ps.setString(2, IsNull(cat.getDescripcion_cat()));
            ps.setString(3, cat.getId_categoria());
            r = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOcategoria.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int EliminarCategoria(String id) {
        Connection con = conexion.getConnection();
        int r = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("DELETE FROM categoria WHERE id_categoria  = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOcategoria.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }
}
