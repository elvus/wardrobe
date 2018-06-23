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
import paquete.modelo.auditoria;

/**
 *
 * @author Elvis
 */
public class DAOauditoria {

    public ArrayList<auditoria> ListarAuditoria() {
        Connection connection = conexion.getConnection();
        ArrayList<auditoria> aud = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM auditoria a INNER JOIN usuarios u ON a.usuario = u.alias_usu INNER JOIN empleados e ON e.id_empleado = u.empleados_id_empleado");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                auditoria au = new auditoria();
                au.setId_auditoria(rs.getString("id_auditoria"));
                au.setFecha(rs.getString("fecha"));
                au.setDescripcion(rs.getString("descripcion"));
                au.setUsuario(rs.getString("usuario"));
                au.setNombre_emp(rs.getString("nombre_emp") + " " + rs.getString("apellido_emp"));
                aud.add(au);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOauditoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return aud;
    }
}
