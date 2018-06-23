/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import static extras.extras.IsInt;
import static extras.extras.IsNull;
import static extras.extras.inputDf;
import static extras.extras.sqlDf;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.caja;
import paquete.modelo.mercaderiasrecibidas;

/**
 *
 * @author Elvis
 */
public class DAOmercaderiasrecibida {

    DAOcaja dao = new DAOcaja();

    public ArrayList<mercaderiasrecibidas> ListarMercaderiasRecibidas() {
        ArrayList<mercaderiasrecibidas> mr = new ArrayList<>();
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mercaderiasrecibidas mr INNER JOIN proveedores p ON mr.proveedores_id_proveedor = p.id_proveedor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mercaderiasrecibidas m = new mercaderiasrecibidas();
                m.setId_mercaderia_recibida(rs.getString("id_mercaderia_recibida"));
                m.setNrofactura_mr(rs.getString("nrofactura_mr"));
                m.setFecha_mr(inputDf(rs.getString("fecha_mr")));
                m.setTotal_mr(rs.getString("total_mr"));
                m.setComentario_mr(rs.getString("comentario_mr"));
                m.setClose_mr(rs.getString("close_mr"));
                m.setNombre_pro(rs.getString("nombre_pro"));
                mr.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return mr;
    }

    public mercaderiasrecibidas BuscarMercaderiasRecibidas(String id) {
        Connection con = conexion.getConnection();
        mercaderiasrecibidas m = new mercaderiasrecibidas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mercaderiasrecibidas m INNER JOIN proveedores p ON m.proveedores_id_proveedor = p.id_proveedor WHERE id_mercaderia_recibida = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m.setNrofactura_mr(rs.getString("nrofactura_mr"));
                m.setFecha_mr(inputDf(rs.getString("fecha_mr")));
                m.setTotal_mr(rs.getString("total_mr"));
                m.setComentario_mr(rs.getString("comentario_mr"));
                m.setClose_mr(rs.getString("close_mr"));
                m.setNombre_pro(rs.getString("nombre_pro"));
                m.setRuc_pro(rs.getString("ruc_pro"));
                m.setDv_pro(rs.getString("dv_pro"));
                m.setTotal_mr(rs.getString("total_mr"));
                m.setTotal_iva_mr(rs.getString("total_iva_mr"));
                m.setComentario_mr(rs.getString("comentario_mr"));
                m.setId_proveedor(rs.getString("proveedores_id_proveedor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public ArrayList<mercaderiasrecibidas> BuscarArticulosRecibidos(String id) {
        Connection con = conexion.getConnection();
        ArrayList<mercaderiasrecibidas> ar = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM articulosrecibidos ar INNER JOIN articulos a ON a.id_articulo = ar.id_articulo WHERE ar.id_mercaderia_recibida = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mercaderiasrecibidas a = new mercaderiasrecibidas();
                a.setCodigo_ar(rs.getString("codigo"));
                a.setBarcode_ar(rs.getString("barcode"));
                a.setDescripcion_ar(rs.getString("descripcion_ar"));
                a.setCantidad_rec(rs.getString("cantidad_rec"));
                a.setId_talle(rs.getString("talle_mr"));
                a.setPrecio_unitario_rec(rs.getString("precio_unitario_rec"));
                a.setSubtotal_rec(rs.getString("subtotal_rec"));
                a.setIva_rec(rs.getString("iva_rec"));
                a.setId_articulo(rs.getString("id_articulo"));
                ar.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }

    public int AgregarMercaderiasRecibidas(mercaderiasrecibidas m) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            CallableStatement cs = con.prepareCall("{call mercaderiasrecibidas (?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, m.getNrofactura_mr());
            cs.setString(2, m.getTotal_mr());
            cs.setString(3, m.getTotal_iva_mr());
            cs.setString(4, sqlDf(m.getFecha_mr()));
            cs.setString(5, IsNull(m.getComentario_mr()));
            cs.setString(6, IsInt(m.getClose_mr()));
            cs.setString(7, m.getId_proveedor());
            cs.registerOutParameter(8, java.sql.Types.INTEGER);
            cs.execute();
            r = cs.getInt(8);
            actualizarSaldoCaja(m.getTotal_mr());
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AgregarArticulosRecibidos(mercaderiasrecibidas m) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO articulosrecibidos VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, m.getId_articulo());
            ps.setString(2, m.getId_mercaderia_recibida());
            ps.setString(3, m.getCantidad_rec());
            ps.setString(4, m.getTalle_mr());
            ps.setString(5, m.getPrecio_unitario_rec());
            ps.setString(6, m.getSubtotal_rec());
            ps.setString(7, m.getIva_rec());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int ActualizarMercaderiasRecibidas(mercaderiasrecibidas m) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE mercaderiasrecibidas SET nrofactura_mr = ?, total_mr = ?, fecha_mr = ?, comentario_mr = ?, proveedores_id_proveedor = ? WHERE id_mercaderia_recibida = ?");
            ps.setString(1, m.getNrofactura_mr());
            ps.setString(2, m.getTotal_mr());
            ps.setString(3, sqlDf(m.getFecha_mr()));
            ps.setString(4, IsNull(m.getComentario_mr()));
            ps.setString(5, m.getId_proveedor());
            ps.setString(6, m.getId_mercaderia_recibida());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int EliminarDetalleMR(String id) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM articulosrecibidos WHERE id_mercaderia_recibida = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOmercaderiasrecibida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void actualizarSaldoCaja(String monto) {
        Connection con = conexion.getConnection();
        caja cj = dao.AperturadeCaja();
        int sd = Integer.valueOf(cj.getSaldo_disponible()) - Integer.valueOf(monto);
        int egr = Integer.valueOf(cj.getEgreso_caja()) + Integer.valueOf(monto);
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE caja SET saldo_disponible = ?, egreso_caja = ? WHERE fecha_caja = ?");
            ps.setInt(1, sd);
            ps.setInt(2, egr);
            ps.setString(3, cj.getFecha_caja());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
