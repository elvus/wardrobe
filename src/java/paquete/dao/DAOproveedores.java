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
import paquete.modelo.proveedores;

/**
 *
 * @author Elvis
 */
public class DAOproveedores {

    public ArrayList<proveedores> ListarProveedores() {
        Connection connection = conexion.getConnection();
        ArrayList<proveedores> p = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from proveedores p INNER JOIN ciudades c ON p.id_ciudad = c.id_ciudad");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proveedores pro = new proveedores();
                pro.setId_proveedor(rs.getString("id_proveedor"));
                pro.setRuc_pro(rs.getString("ruc_pro") + "-" + rs.getString("dv_pro"));
                pro.setDv_pro(rs.getString("dv_pro"));
                pro.setRepresentante_pro(rs.getString("representante_pro"));
                pro.setNombre_pro(rs.getString("nombre_pro"));
                pro.setDireccion_pro(rs.getString("direccion_pro"));
                pro.setNombre_ciudad(rs.getString("nombre_ciudad"));
                pro.setTelefono_pro(rs.getString("telefono_pro"));
                pro.setCelular_pro(rs.getString("celular_pro"));
                pro.setCorreo_pro(rs.getString("correo_pro"));
                p.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOproveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return p;
    }

    public proveedores BuscarProveedores(String id) {
        Connection con = conexion.getConnection();
        proveedores pro = new proveedores();
        try {
            PreparedStatement ps = con.prepareStatement("Select * from proveedores p INNER JOIN ciudades c ON p.id_ciudad = c.id_ciudad WHERE id_proveedor = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro.setRuc_pro(rs.getString("ruc_pro"));
                pro.setDv_pro(rs.getString("dv_pro"));
                pro.setDv_pro(rs.getString("dv_pro"));
                pro.setRepresentante_pro(rs.getString("representante_pro"));
                pro.setNombre_pro(rs.getString("nombre_pro"));
                pro.setDireccion_pro(rs.getString("direccion_pro"));
                pro.setNombre_ciudad(rs.getString("nombre_ciudad"));
                pro.setTelefono_pro(rs.getString("telefono_pro"));
                pro.setCelular_pro(rs.getString("celular_pro"));
                pro.setCorreo_pro(rs.getString("correo_pro"));
                pro.setId_ciudad(rs.getString("id_ciudad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOproveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return pro;
    }

    public int AgregarProveedores(proveedores pro) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO proveedores (ruc_pro, dv_pro, representante_pro, nombre_pro, direccion_pro, telefono_pro, celular_pro, correo_pro, id_ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, pro.getRuc_pro());
            ps.setString(2, pro.getDv_pro());
            ps.setString(3, pro.getRepresentante_pro());
            ps.setString(4, pro.getNombre_pro());
            ps.setString(5, pro.getDireccion_pro());
            ps.setString(6, pro.getTelefono_pro());
            ps.setString(7, pro.getCelular_pro());
            ps.setString(8, pro.getCorreo_pro());
            ps.setString(9, pro.getId_ciudad());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOproveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarProveedores(proveedores pro) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE proveedores SET ruc_pro = ?, dv_pro = ?, representante_pro = ?, nombre_pro = ?, direccion_pro = ?, telefono_pro = ?, celular_pro = ?, correo_pro = ?, id_ciudad = ? WHERE id_proveedor = ?");
            ps.setString(1, pro.getRuc_pro());
            ps.setString(2, pro.getDv_pro());
            ps.setString(3, pro.getRepresentante_pro());
            ps.setString(4, pro.getNombre_pro());
            ps.setString(5, pro.getDireccion_pro());
            ps.setString(6, pro.getTelefono_pro());
            ps.setString(7, pro.getCelular_pro());
            ps.setString(8, pro.getCorreo_pro());
            ps.setString(9, pro.getId_ciudad());
            ps.setString(10, pro.getId_proveedor());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOproveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return r;
    }

    public int CerrarProveedor(String id, String a) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE proveedores SET close_pro = ? WHERE id_proveedor = ?");
            ps.setString(1, a);
            ps.setString(2, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOproveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
