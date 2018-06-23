/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import static extras.Hash.md5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.conexion;
import paquete.modelo.usuarios;

/**
 *
 * @author Elvis
 */
public class DAOusuario {

    public usuarios BuscarUsuario(String alias) {
        Connection connection = conexion.getConnection();
        usuarios usu = new usuarios();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios u INNER JOIN empleados e ON u.empleados_id_empleado = e.id_empleado INNER JOIN ciudades c ON c.id_ciudad = e.id_ciudad INNER JOIN departamentos d ON d.id_departamento = c.id_departamento INNER JOIN contacto_emp cont ON cont.id_empleado = e.id_empleado INNER JOIN cargos car ON car.id_cargo = e.id_cargo WHERE alias_usu = ?");
            ps.setString(1, alias);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usu.setId_usuario(rs.getString("id_usuario"));
                usu.setAlias(rs.getString("alias_usu"));
                usu.setNombre_emp(rs.getString("nombre_emp"));
                usu.setApellido_emp(rs.getString("apellido_emp"));
                usu.setNdocumento_emp(rs.getString("ndocumento_emp"));
                usu.setCorreo_cont(rs.getString("correo_cont"));
                usu.setCelular_cont(rs.getString("celular_cont"));
                usu.setTelefono_cont(rs.getString("telefono_cont"));
                usu.setCargo_emp(rs.getString("nombre_cargo"));
                usu.setFnac_emp(rs.getString("fnac_emp"));
                usu.setFecha_ingreso_emp(rs.getString("fecha_ingreso_emp"));
                usu.setEstado_emp(rs.getString("estado_emp"));
                usu.setNombre_ciudad(rs.getString("nombre_ciudad"));
                usu.setNombre_dep(rs.getString("nombre_dep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return usu;
    }

    public ArrayList<usuarios> ListarUsuarios() {
        Connection connection = conexion.getConnection();
        ArrayList<usuarios> users = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios u INNER JOIN empleados e ON u.empleados_id_empleado = e.id_empleado INNER JOIN ciudades c ON c.id_ciudad = e.id_ciudad INNER JOIN departamentos d ON d.id_departamento = c.id_departamento INNER JOIN contacto_emp cont ON cont.id_empleado = e.id_empleado INNER JOIN cargos car ON car.id_cargo = e.id_cargo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios usu = new usuarios();
                usu.setId_usuario(rs.getString("id_usuario"));
                usu.setAlias(rs.getString("alias_usu"));
                usu.setNombre_emp(rs.getString("nombre_emp") + " " + rs.getString("apellido_emp"));
                usu.setNdocumento_emp(rs.getString("ndocumento_emp"));
                usu.setCorreo_cont(rs.getString("correo_cont"));
                usu.setCelular_cont(rs.getString("celular_cont"));
                usu.setTelefono_cont(rs.getString("telefono_cont"));
                usu.setCargo_emp(rs.getString("nombre_cargo"));
                usu.setFnac_emp(rs.getString("fnac_emp"));
                usu.setFecha_ingreso_emp(rs.getString("fecha_ingreso_emp"));
                usu.setEstado_emp(rs.getString("estado_emp"));
                usu.setNombre_ciudad(rs.getString("nombre_ciudad"));
                usu.setNombre_dep(rs.getString("nombre_dep"));
                users.add(usu);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return users;
    }

    public int AgregarUsuarios(usuarios user) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement cre = connection.prepareStatement("CREATE USER ?@'localhost' IDENTIFIED BY ?");
            cre.setString(1, user.getAlias());
            cre.setString(2, user.getPassword());
            cre.executeUpdate();
            PreparedStatement grant = connection.prepareStatement("GRANT ALL PRIVILEGES ON wardrove.* TO ?@'localhost'");
            grant.setString(1, user.getAlias());
            grant.executeUpdate();
            PreparedStatement ps = connection.prepareStatement("Insert into usuarios(alias_usu, password_usu, fcreacion_usu, empleados_id_empleado) VALUES (?, ?, now(), ?)");
            ps.setString(1, user.getAlias());
            ps.setString(2, md5(user.getPassword()));
            ps.setString(3, user.getId_empleado());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarPassword(usuarios user) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("update user set password=PASSWORD(?) WHERE user = ?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getAlias());
            r = ps.executeUpdate();
            ps = con.prepareStatement("UPDATE usuarios set password_usu = ? WHERE alias_usu = ?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getAlias());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int VerificarAlias_usu(String alias) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE alias_usu = ?");
            ps.setString(1, alias);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ArrayList<usuarios> BuscarPermisosxUsuario(String id_usuario) {
        Connection con = conexion.getConnection();
        ArrayList<usuarios> up = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios_has_permisos WHERE usuarios_id_usuario = ?");
            ps.setString(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios u = new usuarios();
                u.setId_usuario(rs.getString("usuarios_id_usuario"));
                u.setId_permiso(rs.getString("permisos_id_permiso"));
                u.setVer(rs.getString("ver"));
                u.setEditar(rs.getString("editar"));
                u.setAgregar(rs.getString("agregar"));
                up.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return up;
    }
}
