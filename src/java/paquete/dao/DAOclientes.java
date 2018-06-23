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
import paquete.modelo.clientes;
import static extras.extras.IsNull;

/**
 *
 * @author Elvis
 */
public class DAOclientes {

    public ArrayList<clientes> ListarClientes() {
        ArrayList<clientes> cli = new ArrayList<>();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from clientes cli INNER JOIN ciudades c ON cli.id_ciudad = c.id_ciudad");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes c = new clientes();
                c.setId_cliente(rs.getString("id_cliente"));
                c.setNombre_cli(rs.getString("nombre_apellido_cli"));
                c.setDocumento_cli(rs.getString("documento_cli") + "-" + rs.getString("dv_cli"));
                c.setCelular_cli(rs.getString("celular_cli"));
                c.setNombre_ciudad(rs.getString("nombre_ciudad"));
                c.setDireccion_cli(rs.getString("direccion_cli"));
                cli.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOclientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return cli;
    }

    public clientes BuscarClientes(String id) {
        clientes c = new clientes();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from clientes cli INNER JOIN ciudades c ON cli.id_ciudad = c.id_ciudad WHERE id_cliente = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_cliente(rs.getString("id_cliente"));
                c.setNombre_cli(rs.getString("nombre_apellido_cli"));
                c.setDocumento_cli(rs.getString("documento_cli"));
                c.setDv_cli(rs.getString("dv_cli"));
                c.setTelefono_cli(rs.getString("telefono_cli"));
                c.setCorreo_cli(rs.getString("correo_cli"));
                c.setCelular_cli(rs.getString("celular_cli"));
                c.setNombre_ciudad(rs.getString("nombre_ciudad"));
                c.setDireccion_cli(rs.getString("direccion_cli"));
                c.setId_ciudad(rs.getString("id_ciudad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOclientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return c;
    }

    public int AgregarClientes(clientes cli) {
        int r = 0;
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes (nombre_apellido_cli, documento_cli, dv_cli, telefono_cli, correo_cli, celular_cli, direccion_cli, id_ciudad) values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, cli.getNombre_cli());
            ps.setString(2, cli.getDocumento_cli());
            ps.setString(3, cli.getDv_cli());
            ps.setString(4, IsNull(cli.getTelefono_cli()));
            ps.setString(5, IsNull(cli.getCorreo_cli()));
            ps.setString(6, cli.getCelular_cli());
            ps.setString(7, IsNull(cli.getDireccion_cli()));
            ps.setString(8, cli.getId_ciudad());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOclientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarClientes(clientes cli) {
        int r = 0;
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET nombre_apellido_cli = ?, documento_cli = ?, dv_cli = ?, telefono_cli = ?, correo_cli = ?, celular_cli = ?, direccion_cli = ?, id_ciudad = ? WHERE id_cliente = ?");
            ps.setString(1, cli.getNombre_cli());
            ps.setString(2, cli.getDocumento_cli());
            ps.setString(3, cli.getDv_cli());
            ps.setString(4, IsNull(cli.getTelefono_cli()));
            ps.setString(5, IsNull(cli.getCorreo_cli()));
            ps.setString(6, cli.getCelular_cli());
            ps.setString(7, IsNull(cli.getDireccion_cli()));
            ps.setString(8, cli.getId_ciudad());
            ps.setString(9, cli.getId_cliente());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOclientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }
}
