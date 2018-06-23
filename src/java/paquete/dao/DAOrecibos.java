/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.recibos;

/**
 *
 * @author Elvis
 */
public class DAOrecibos {

    public ArrayList<recibos> ListarRecibos() {
        Connection con = conexion.getConnection();
        ArrayList<recibos> rec = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recibos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recibos r = new recibos();
                r.setId_recibo(rs.getString("id_recibo"));
                r.setFecha_rec(rs.getString("fecha_rec"));
                r.setMonto_rec(rs.getString("monto_rec"));
                r.setComentario_rec(rs.getString("comentario_rec"));
                rec.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return rec;
    }

    public recibos BuscarRecibos(String id) {
        Connection con = conexion.getConnection();
        recibos r = new recibos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recibos WHERE id_recibos = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r.setFecha_rec(rs.getString("fecha_rec"));
                r.setMonto_rec(rs.getString("monto_rec"));
                r.setComentario_rec(rs.getString("comentario_rec"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int AgregarRecibos(recibos re) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            CallableStatement cs = con.prepareCall("{recibos (?, ?, ?, ?)}");
            cs.setString(1, re.getFecha_rec());
            cs.setString(2, re.getMonto_rec());
            cs.setString(3, re.getComentario_rec());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.execute();
            r = cs.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }

        return r;
    }

    public int ActualizarRecibos(recibos re) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE recibos SET fecha_rec = ?, monto_rec = ?, comentario_rec = ?");
            ps.setString(1, re.getFecha_rec());
            ps.setString(2, re.getMonto_rec());
            ps.setString(3, re.getComentario_rec());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int AgregarDetalleRec(recibos re) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ventas_has_recibos (ventas_id_venta, monto_rec, concepto_rec) VALUES (?, ?)");
            ps.setString(1, re.getId_recibo());
            ps.setString(2, re.getId_venta());
            ps.setString(3, re.getMonto_rec());
            ps.setString(4, re.getConcepto_rec());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int ActualizarDetalleRec(recibos re) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE ventas_has_recibos SET ventas_id_venta = ?, monto_rec = ?, concepto_rec = ?");
            ps.setString(1, re.getId_venta());
            ps.setString(2, re.getMonto_rec());
            ps.setString(3, re.getConcepto_rec());
            ps.setString(4, re.getId_recibo());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOrecibos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
