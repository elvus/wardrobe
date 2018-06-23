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
import paquete.modelo.caja;

/**
 *
 * @author Elvis
 */
public class DAOcaja {

    public caja AperturadeCaja() {
        Connection con = conexion.getConnection();
        caja c = new caja();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM caja ORDER BY id_caja desc LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setFecha_caja(rs.getString("fecha_caja"));
                c.setSaldo_anterior(rs.getString("saldo_anterior"));
                c.setSaldo_disponible(rs.getString("saldo_disponible"));
                c.setIngreso_caja(rs.getString("ingreso_caja"));
                c.setEgreso_caja(rs.getString("egreso_caja"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public int abrirCaja(caja c) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO caja (fecha_caja, saldo_anterior, saldo_disponible) VALUES (NOW(), ?, ?)");
            ps.setString(1, c.getSaldo_anterior());
            ps.setString(2, c.getSaldo_disponible());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<caja> ReporteCaja() {
        Connection con = conexion.getConnection();
        ArrayList<caja> cj = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM caja");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caja c = new caja();
                c.setId_caja(rs.getString("id_caja"));
                c.setFecha_caja(rs.getString("fecha_caja"));
                c.setIngreso_caja(rs.getString("Ingreso_caja"));
                c.setEgreso_caja(rs.getString("egreso_caja"));
                c.setSaldo_anterior(rs.getString("saldo_anterior"));
                c.setSaldo_disponible(rs.getString("saldo_disponible"));
                cj.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOcaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cj;
    }
}
