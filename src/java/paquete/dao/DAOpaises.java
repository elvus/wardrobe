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
import paquete.modelo.paises;

/**
 *
 * @author Elvis
 */
public class DAOpaises {

    public ArrayList<paises> ListarPaises() {
        Connection connection = conexion.getConnection();
        ArrayList<paises> pa = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from paises");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paises p = new paises();
                p.setId_pais(rs.getString("id_pais"));
                p.setNombre_pais(rs.getString("nombre_pais"));
                p.setCodigo_pais(rs.getString("codigo_pais"));
                p.setNacionalidad_pais(rs.getString("nacionalidad_pais"));
                pa.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOpaises.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return pa;
    }
}
