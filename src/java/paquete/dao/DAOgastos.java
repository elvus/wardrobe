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
import paquete.modelo.gastos;

/**
 *
 * @author Elvis
 */
public class DAOgastos {

    public ArrayList<gastos> ListarGastos() {
        Connection con = conexion.getConnection();
        ArrayList<gastos> gas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM gastos g INNER JOIN proveedores p ON g.proveedores_id_proveedor = p.id_proveedor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gastos g = new gastos();
                g.setId_gastos(rs.getString("id_gasto"));
                g.setTotal_gasto(rs.getString("total_gasto"));
                g.setFecha_gasto(rs.getString("fecha_gasto"));
                g.setNombre_pro(rs.getString("nombre_pro"));
                gas.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return gas;
    }

    public gastos BuscarGastos(String id) {
        Connection con = conexion.getConnection();
        gastos g = new gastos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM gastos INNER JOIN proveedores p ON g.proveedores_id_proveedor = p.id_proveedor WHERE id_gasto = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                g.setId_gastos(rs.getString("id_gasto"));
                g.setTotal_gasto(rs.getString("total_gasto"));
                g.setFecha_gasto(rs.getString("fecha_gasto"));
                g.setNombre_pro(rs.getString("nombre_pro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

    public int AgregarGastos(gastos g) {
        int r = 0;
        Connection con = conexion.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call gastos(?, ?, ?, ?)}");
            cs.setString(1, g.getTotal_gasto());
            cs.setString(2, g.getFecha_gasto());
            cs.setString(3, g.getId_proveedor());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.execute();
            r = cs.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int AgregarDetalle(gastos g) {
        int r = 0;
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO gastos_has_articulos VALUES (?, ?, ?, ?)");
            ps.setString(1, g.getId_gastos());
            ps.setString(2, g.getId_articulo());
            ps.setString(3, g.getConcepto_gasto());
            ps.setString(4, g.getMonto_gasto());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }

        return r;
    }

    public int ActualizarGasto(gastos g) {
        int r = 0;
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE gastos SET total_gasto = ?, fecha_gasto = ?, proveedores_id_proveedor = ? WHERE id_gasto  = ?");
            ps.setString(1, g.getTotal_gasto());
            ps.setString(2, g.getFecha_gasto());
            ps.setString(3, g.getId_proveedor());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int ActualizarDetalle(gastos g) {
        int r = 0;
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE gastos_has_articulos SET articulos_id_articulo = ?, concepto_gasto  = ?, monto_gasto = ? WHERE id_gasto = ?");
            ps.setString(1, g.getId_articulo());
            ps.setString(2, g.getConcepto_gasto());
            ps.setString(3, g.getMonto_gasto());
            ps.setString(4, g.getId_gastos());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOgastos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }
}
