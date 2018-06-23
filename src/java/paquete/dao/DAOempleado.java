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
import paquete.modelo.empleados;

/**
 *
 * @author Elvis
 */
public class DAOempleado {

    public ArrayList<empleados> ListarEmpleados() {
        Connection connection = conexion.getConnection();
        ArrayList<empleados> emp = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM empleados e  INNER JOIN ciudades c ON c.id_ciudad = e.id_ciudad INNER JOIN departamentos d ON d.id_departamento = c.id_departamento INNER JOIN contacto_emp cont ON cont.id_empleado = e.id_empleado INNER JOIN cargos car on car.id_cargo = e.id_cargo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleados e = new empleados();
                e.setId_empleado(rs.getString("id_empleado"));
                e.setNombre_emp(rs.getString("nombre_emp") + " " + rs.getString("apellido_emp"));
                e.setNdocumento_emp(rs.getString("ndocumento_emp"));
                e.setCorreo_cont(rs.getString("correo_cont"));
                e.setCelular_cont(rs.getString("celular_cont"));
                e.setCargo_emp(rs.getString("nombre_cargo"));
                e.setEstado_emp(rs.getString("estado_emp"));
                emp.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return emp;
    }

    public empleados BuscarEmpleados(String id) {
        Connection con = conexion.getConnection();
        empleados emp = new empleados();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados e INNER JOIN contacto_emp ce ON e.id_empleado = ce.id_empleado WHERE e.id_empleado = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp.setId_empleado(rs.getString("id_empleado"));
                emp.setNombre_emp(rs.getString("nombre_emp"));
                emp.setApellido_emp(rs.getString("apellido_emp"));
                emp.setNdocumento_emp(rs.getString("ndocumento_emp"));
                emp.setFecha_ingreso_emp(rs.getString("fecha_ingreso_emp"));
                emp.setFnac_emp(rs.getString("fnac_emp"));
                emp.setEstado_emp(rs.getString("estado_emp"));
                emp.setId_ciudad(rs.getString("id_ciudad"));
                emp.setId_cargo(rs.getString("id_cargo"));
                emp.setTelefono_cont(rs.getString("telefono_cont"));
                emp.setCorreo_cont(rs.getString("correo_cont"));
                emp.setCelular_cont(rs.getString("celular_cont"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emp;
    }

    public int AgregarEmpleados(empleados e) {
        Connection connection = conexion.getConnection();
        System.out.println(e.getId_cargo());
        int r = 0;
        try {
            CallableStatement proc = connection.prepareCall("{call empleados(?, ?, ?, ?, ?, ?, ?, ?)}");
            proc.setString(1, e.getNombre_emp());
            proc.setString(2, e.getApellido_emp());
            proc.setString(3, e.getNdocumento_emp());
            proc.setString(4, e.getFecha_ingreso_emp());
            proc.setString(5, e.getFnac_emp());
            proc.setString(6, e.getId_ciudad());
            proc.setString(7, e.getId_cargo());
            proc.registerOutParameter(8, java.sql.Types.INTEGER);
            proc.execute();
            r = proc.getInt(8);
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int AgregarContacto(empleados emp) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO contacto_emp(telefono_cont, correo_cont, celular_cont, principal_cont, id_empleado) values(?, ?, ?, ?, ?)");
            ps.setString(1, emp.getTelefono_cont());
            ps.setString(2, emp.getCorreo_cont());
            ps.setString(3, emp.getCelular_cont());
            ps.setString(4, emp.getPrincipal_cont());
            ps.setString(5, emp.getId_empleado());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public ArrayList<empleados> SinUsuario() {
        Connection connection = conexion.getConnection();
        ArrayList<empleados> emp = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM empleados e WHERE e.id_empleado NOT IN(SELECT u.empleados_id_empleado from usuarios u)");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleados e = new empleados();
                e.setId_empleado(rs.getString("id_empleado"));
                e.setNombre_emp(rs.getString("nombre_emp") + " " + rs.getString("apellido_emp"));
                emp.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return emp;
    }

    public int ActualizarEmpleado(empleados emp) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE empleados SET nombre_emp = ?, apellido_emp = ?, ndocumento_emp = ?, fecha_ingreso_emp = ?, fnac_emp =?, id_ciudad = ?, id_cargo = ? WHERE id_empleado = ?");
            ps.setString(1, emp.getNombre_emp());
            ps.setString(2, emp.getApellido_emp());
            ps.setString(3, emp.getNdocumento_emp());
            ps.setString(4, emp.getFecha_ingreso_emp());
            ps.setString(5, emp.getFnac_emp());
            ps.setString(6, emp.getId_ciudad());
            ps.setString(7, emp.getId_cargo());
            ps.setString(8, emp.getId_empleado());
            r = ps.executeUpdate();
            if (r > 0) {
                r = actualizarContactoEmp(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int actualizarContactoEmp(empleados emp) {
        Connection con = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE contacto_emp SET telefono_cont = ?, correo_cont = ?, celular_cont = ? WHERE id_empleado = ?");
            ps.setString(1, emp.getTelefono_cont());
            ps.setString(2, emp.getCorreo_cont());
            ps.setString(3, emp.getCelular_cont());
            ps.setString(4, emp.getId_empleado());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOempleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
