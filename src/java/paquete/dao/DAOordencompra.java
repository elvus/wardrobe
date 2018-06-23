/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

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
import paquete.modelo.ordendecompra;

/**
 *
 * @author Elvis
 */
public class DAOordencompra {

    public ArrayList<ordendecompra> ListarOrdenes() {
        Connection connection = conexion.getConnection();
        ArrayList<ordendecompra> oc = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordendecompra oc INNER JOIN proveedores p ON oc.proveedores_id_proveedor = p.id_proveedor WHERE recibido_ord = 0 AND close_ord = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordendecompra ord = new ordendecompra();
                ord.setId_orden_compra(rs.getString("id_orden_compra"));
                ord.setComentario_ord(rs.getString("comentario_ord"));
                ord.setFecha_ord(rs.getString("fecha_ord"));
                ord.setAprobado_ord(rs.getString("aprobado_ord"));
                ord.setRecibido_ord(rs.getString("recibido_ord"));
                ord.setTotal_ord(rs.getString("total_ord"));
                ord.setTotal_iva_ord(rs.getString("total_iva_ord"));
                ord.setNombre_pro(rs.getString("nombre_pro"));
                oc.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return oc;
    }

    public int AgregarOrdenes(ordendecompra ord) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            CallableStatement ps = connection.prepareCall("{call orden(?, ?, ?, ?, ?)}");
            ps.setString(1, IsNull(ord.getComentario_ord()));
            ps.setString(2, sqlDf(ord.getFecha_ord()));
            ps.setString(3, ord.getTotal_ord());
            ps.setString(4, ord.getId_proveedor());
            ps.registerOutParameter(5, java.sql.Types.INTEGER);
            ps.execute();
            r = ps.getInt(5);
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public ordendecompra BuscarOrdenes(String id) {
        Connection connection = conexion.getConnection();
        ordendecompra ord = new ordendecompra();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordendecompra oc INNER JOIN proveedores p ON oc.proveedores_id_proveedor = p.id_proveedor WHERE id_orden_compra = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ord.setId_orden_compra(id);
                ord.setComentario_ord(rs.getString("comentario_ord"));
                ord.setFecha_ord(inputDf(rs.getString("fecha_ord")));
                ord.setAprobado_ord(rs.getString("aprobado_ord"));
                ord.setRecibido_ord(rs.getString("recibido_ord"));
                ord.setTotal_ord(rs.getString("total_ord"));
                ord.setTotal_iva_ord(rs.getString("total_iva_ord"));
                ord.setNombre_pro(rs.getString("nombre_pro"));
                ord.setId_proveedor(rs.getString("id_proveedor"));
                ord.setRuc_pro(rs.getString("ruc_pro"));
                ord.setDv_pro(rs.getString("dv_pro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return ord;
    }

    public ArrayList<ordendecompra> BuscarDetalleOrd(String id) {
        Connection con = conexion.getConnection();
        ArrayList<ordendecompra> ord = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM articulosordenados ao INNER JOIN articulos a ON ao.id_articulo = a.id_articulo WHERE id_orden_compra = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordendecompra or = new ordendecompra();
                or.setId_articulo(rs.getString("id_articulo"));
                or.setId_orden_compra(rs.getString("id_orden_compra"));
                or.setDescripcion_ar(rs.getString("descripcion_ar"));
                or.setPrecio_ord(rs.getString("precio_ord"));
                or.setIva_ord(rs.getString("iva_ord"));
                or.setCantidad_ord(rs.getString("cantidad_ord"));
                or.setCodigo_ar_ord(rs.getString("codigo_ord"));
                or.setId_talle(rs.getString("talles_id_talle"));
                ord.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ord;
    }

    public int ActualizarOrden(ordendecompra ord) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ordendecompra SET comentario_ord = ?, fecha_ord = ?, total_ord = ?, proveedores_id_proveedor = ?"
                    + "WHERE id_orden_compra = ?");
            ps.setString(1, IsNull(ord.getComentario_ord()));
            ps.setString(2, sqlDf(ord.getFecha_ord()));
            ps.setString(3, ord.getTotal_ord());
            ps.setString(4, ord.getId_proveedor());
            ps.setString(5, ord.getId_orden_compra());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AgregarArticulosOrdenardos(ordendecompra ord) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO articulosordenados(id_articulo, id_orden_compra, precio_ord, iva_ord, cantidad_ord, codigo_ord) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, ord.getId_articulo());
            ps.setString(2, ord.getId_orden_compra());
            ps.setString(3, ord.getPrecio_ord());
            ps.setString(4, ord.getIva_ord());
            ps.setString(5, ord.getCantidad_ord());
            ps.setString(6, ord.getCodigo_ar_ord());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int CerrarOrdendeCompra(String id, String a) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ordendecompra SET close_ord = ? WHERE id_orden_compra = ?");
            ps.setString(1, a);
            ps.setString(2, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int AprobarOrden(String id, String a) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ordendecompra SET aprobado_ord = ? WHERE id_orden_compra = ?");
            ps.setString(1, a);
            ps.setString(2, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int RecibirOrden(String id) {
        Connection con = conexion.getConnection();
        int r = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE ordendecompra SET recibido_ord = 1 WHERE id_orden_compra = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int EliminarDetArtOrd(String id) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM articulosordenados WHERE id_orden_compra = ?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOordencompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
