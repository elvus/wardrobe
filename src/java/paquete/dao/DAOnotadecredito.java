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
import paquete.modelo.ventas;

/**
 *
 * @author Elvis
 */
public class DAOnotadecredito {

    public ArrayList<ventas> ListarNotasCredito() {
        ArrayList<ventas> nc = new ArrayList<>();
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id_cliente WHERE id_tipocomprobante = 2");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventas v = new ventas();
                v.setId_venta(rs.getString("id_venta"));
                v.setId_cliente(rs.getString("id_cliente"));
                v.setFecha_ven(rs.getString("fecha_ven"));
                v.setTotal_ven(rs.getString("total_ven"));
                v.setTotal_iva_ven(rs.getString("total_iva_ven"));
                v.setNro_factura(rs.getString("nro_factura"));
                v.setNombre_cli(rs.getString("nombre_apellido_cli"));
                v.setDocumento_cli(rs.getString("documento_cli"));
                v.setDv_cli(rs.getString("dv_cli"));
                nc.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOnotadecredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nc;
    }

    public ventas BuscarNotadeCredito(String id) {
        ventas v = new ventas();
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id_cliente WHERE id_tipocomprobante = 2 and id_venta = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setId_venta(rs.getString("id_venta"));
                v.setId_cliente(rs.getString("id_cliente"));
                v.setFecha_ven(rs.getString("fecha_ven"));
                v.setTotal_ven(rs.getString("total_ven"));
                v.setTotal_iva_ven(rs.getString("total_iva_ven"));
                v.setNro_factura(rs.getString("nro_factura"));
                v.setNombre_cli(rs.getString("nombre_cli"));
                v.setDocumento_cli(rs.getString("documento_cli"));
                v.setDv_cli(rs.getString("dv_cli"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOnotadecredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public int AgregarNotasdeCredito(ventas v) {
        int r = 0;
        Connection con = conexion.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call notadecredito(?, ?, ?, ?, ?)}");
            cs.setString(1, v.getId_cliente());
            cs.setString(2, v.getTotal_ven());
            cs.setString(3, v.getTotal_iva_ven());
            cs.setString(4, v.getNro_factura());
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.executeUpdate();
            r = cs.getInt(5);
            VincularFactura(v.getNro_factura(), String.valueOf(r));
        } catch (SQLException ex) {
            Logger.getLogger(DAOnotadecredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AgregarDetalleCredito(ventas v) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO detalleventa(id_venta, id_articulo, cantidad_ven, precio_ven, iva_ven, subtotal_ven, talles_id_talle) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, v.getId_venta());
            ps.setString(2, v.getId_articulo());
            ps.setString(3, v.getCantidad_ven());
            ps.setString(4, v.getPrecio_ven());
            ps.setString(5, v.getIva());
            ps.setString(6, v.getSubtotal_ven());
            ps.setString(7, v.getTalle_ven());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOnotadecredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void VincularFactura(String id, String nro_fac) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE ventas SET nro_factura = ? WHERE id_venta = ?");
            ps.setString(1, nro_fac);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOnotadecredito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
