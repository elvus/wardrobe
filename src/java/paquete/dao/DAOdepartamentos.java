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
import paquete.modelo.departamentos;

/**
 *
 * @author Elvis
 */
public class DAOdepartamentos {

    public ArrayList<departamentos> ListarDepartamentos() {
        ArrayList<departamentos> d = new ArrayList<>();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM departamentos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departamentos dptos = new departamentos();
                dptos.setId_departamento(rs.getString("id_departamento"));
                dptos.setNombre_dep(rs.getString("nombre_dep"));
                d.add(dptos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOdepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return d;
    }

    public departamentos BuscarDepartamentos(String id) {
        departamentos dptos = new departamentos();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM departamentos WHERE id_departamento = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dptos.setNombre_dep(rs.getString("nombre_dep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOdepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return dptos;
    }

    public int AgregarDepartamentos(departamentos d) {
        int r = 0;
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO departamentos (nombre_dep) VALUES (?)");
            ps.setString(1, d.getNombre_dep());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOdepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarDepartamentos(departamentos d) {
        int r = 0;
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE departamentos SET nombre_dep = ? WHERE id_departamento=?");
            System.out.println(d.getNombre_dep() + " " + d.getId_departamento());
            ps.setString(1, d.getNombre_dep());
            ps.setString(2, d.getId_departamento());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOdepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int EliminarDepartamentos(String id) {
        int r = 0;
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM departamentos WHERE id_departamento=?");
            ps.setString(1, id);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOdepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }
}
