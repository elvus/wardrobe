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
import paquete.modelo.caja;
import paquete.modelo.ventas;

/**
 *
 * @author Elvis
 */
public class DAOventas {

    DAOcaja dao = new DAOcaja();

    public ArrayList<ventas> ListarVentas() {
        Connection con = conexion.getConnection();
        ArrayList<ventas> ven = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas v INNER JOIN clientes c ON c.id_cliente = v.id_cliente WHERE id_tipocomprobante = 1 and nro_factura = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventas v = new ventas();
                v.setId_venta(rs.getString("id_venta"));
                v.setTipo_ven(rs.getString("tipo_venta"));
                v.setAprobado_ven(rs.getString("aprobado_ven"));
                v.setFecha_ven(rs.getString("fecha_ven"));
                v.setTotal_ven(rs.getString("total_ven"));
                v.setClose_ven(rs.getString("close_ven"));
                v.setNombre_cli(rs.getString("nombre_apellido_cli"));
                v.setDocumento_cli(rs.getString("documento_cli"));
                v.setDv_cli(rs.getString("dv_cli"));
                ven.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ven;
    }

    public ventas BuscarVentas(String id) {
        Connection con = conexion.getConnection();
        ventas v = new ventas();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas v INNER JOIN clientes c ON c.id_cliente = v.id_cliente WHERE id_venta = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setId_venta(rs.getString("id_venta"));
                v.setTipo_ven(rs.getString("tipo_venta"));
                v.setAprobado_ven(rs.getString("aprobado_ven"));
                v.setFecha_ven(rs.getString("fecha_ven"));
                v.setTotal_ven(rs.getString("total_ven"));
                v.setClose_ven(rs.getString("close_ven"));
                v.setNombre_cli(rs.getString("nombre_apellido_cli"));
                v.setDocumento_cli(rs.getString("documento_cli"));
                v.setDv_cli(rs.getString("dv_cli"));
                v.setId_cliente(rs.getString("id_cliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public ArrayList<ventas> BuscarDetalleVentas(String id) {
        Connection con = conexion.getConnection();
        ArrayList<ventas> ven = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM detalleventa v INNER JOIN articulos a ON v.id_articulo = a.id_articulo WHERE id_venta = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventas v = new ventas();
                v.setId_venta(rs.getString("id_venta"));
                v.setId_articulo(rs.getString("id_articulo"));
                v.setCodigo_ar(rs.getString("codigo"));
                v.setDescripcion_ar(rs.getString("descripcion_ar"));
                v.setCantidad_ven(rs.getString("cantidad_ven"));
                v.setIva(rs.getString("iva_ven"));
                v.setPrecio_ven(rs.getString("precio_ven"));
                v.setSubtotal_ven(rs.getString("subtotal_ven"));
                v.setId_talle(rs.getString("talles_id_talle"));
                ven.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ven;
    }

    public int AgregarVenta(ventas v) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            CallableStatement cs = con.prepareCall("{call ventas(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, v.getId_cliente());
            cs.setString(2, v.getTipo_ven());
            cs.setString(3, v.getTotal_ven());
            cs.setString(4, v.getTotal_iva_ven());
            cs.setString(5, "1");
            cs.registerOutParameter(6, java.sql.Types.INTEGER);
            cs.executeUpdate();
            r = cs.getInt(6);
            actualizarSaldoCaja(v.getTotal_ven());
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AgregarDetalleVenta(ventas v) {
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
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AprobarVenta(String id, String a) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE ventas SET aprobado_ven = ? WHERE id_venta = ?");
            ps.setString(1, a);
            ps.setString(2, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AnularVenta(String id, String a) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE ventas SET close_ven = ? WHERE id_venta = ?");
            ps.setString(1, id);
            ps.setString(2, a);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<ventas> VentasPorCobrar() {
        Connection con = conexion.getConnection();
        ArrayList<ventas> ven = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas v INNER JOIN clientes c ON c.id_cliente = v.id_cliente WHERE tipo_venta = 1 and cobrado_ven = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventas v = new ventas();
                v.setId_venta(rs.getString("id_venta"));
                v.setTipo_ven(rs.getString("tipo_venta"));
                v.setAprobado_ven(rs.getString("aprobado_ven"));
                v.setFecha_ven(rs.getString("fecha_ven"));
                v.setTotal_ven(rs.getString("total_ven"));
                v.setClose_ven(rs.getString("close_ven"));
                v.setNombre_cli(rs.getString("nombre_apellido_cli"));
                v.setDocumento_cli(rs.getString("documento_cli"));
                v.setDv_cli(rs.getString("dv_cli"));
                v.setId_cliente(rs.getString("id_cliente"));
                ven.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ven;
    }

    public ArrayList<ventas> MasVendidos() {
        ArrayList<ventas> ven = new ArrayList<>();
        Connection con = conexion.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT SUM(dv.cantidad_ven) AS cant, dv.id_articulo, a.descripcion_ar FROM detalleventa dv INNER JOIN articulos a ON dv.id_articulo = a.id_articulo GROUP BY id_articulo");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("cant") > 10) {
                    ventas v = new ventas();
                    v.setId_articulo(rs.getString("dv.id_articulo"));
                    v.setDescripcion_ar(rs.getString("a.descripcion_ar"));
                    v.setCantidad_ven(rs.getString("cant"));
                    ven.add(v);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ven;
    }

    public void actualizarSaldoCaja(String monto) {
        caja cj = dao.AperturadeCaja();
        Connection con = conexion.getConnection();
        int sd = Integer.valueOf(cj.getSaldo_disponible()) + Integer.valueOf(monto);
        int ing = Integer.valueOf(cj.getIngreso_caja()) + Integer.valueOf(monto);
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE caja SET saldo_disponible = ?, ingreso_caja = ? WHERE fecha_caja = ?");
            ps.setInt(1, sd);
            ps.setInt(2, ing);
            ps.setString(3, cj.getFecha_caja());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOcaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
