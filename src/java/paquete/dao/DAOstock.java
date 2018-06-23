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
import paquete.modelo.stock;

/**
 *
 * @author Elvis
 */
public class DAOstock {

    public ArrayList<stock> ListarStock() {
        Connection con = conexion.getConnection();
        ArrayList<stock> stock = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stock s INNER JOIN articulos a ON s.articulos_id_articulo = a.id_articulo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock s = new stock();
                s.setId_stock(rs.getString("id_stock"));
                s.setCantidad(rs.getString("cantidad_stock"));
                s.setDescripcion_ar(rs.getString("descripcion_ar"));
                stock.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOstock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    public ArrayList<stock> ListarStockBajo() {
        Connection con = conexion.getConnection();
        ArrayList<stock> stock = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stock s INNER JOIN articulos a ON s.articulos_id_articulo = a.id_articulo WHERE s.cantidad_stock <= 3");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock s = new stock();
                s.setId_stock(rs.getString("id_stock"));
                s.setCantidad(rs.getString("cantidad_stock"));
                s.setDescripcion_ar(rs.getString("descripcion_ar"));
                stock.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOstock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }

    public stock BuscarStock(String id_articulo) {
        Connection con = conexion.getConnection();
        stock s = new stock();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stock s INNER JOIN articulos a ON s.articulos_id_articulo = a.id_articulo WHERE articulos_id_articulo = ?");
            ps.setString(1, id_articulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setId_stock(rs.getString("id_stock"));
                s.setCantidad(rs.getString("cantidad"));
                s.setDescripcion_ar(rs.getString("descripcion_ar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOstock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
