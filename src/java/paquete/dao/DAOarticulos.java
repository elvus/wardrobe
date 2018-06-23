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
import paquete.modelo.articulos;
import static extras.extras.*;

/**
 *
 * @author Elvis
 */
public class DAOarticulos {

    public ArrayList<articulos> ListarArticulos() {
        Connection connection = conexion.getConnection();
        ArrayList<articulos> art = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("Select *  from articulos a INNER JOIN marcas m ON m.id_marca = a.marcas_id_marca INNER JOIN categoria c ON c.id_categoria = a.categoria_id_categoria");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                articulos a = new articulos();
                a.setId_articulo(rs.getString("id_articulo"));
                a.setCodigo_ar(rs.getString("codigo"));
                a.setId_marca(rs.getString("id_marca"));
                a.setNombre_marca(rs.getString("nombre_marca"));
                a.setPrecio_ar(rs.getString("precio_ar"));
                a.setPrecio_compra_ar(rs.getString("precio_compra_ar"));
                a.setDescripcion_ar(rs.getString("descripcion_ar"));
                a.setId_talle(rs.getString("talles_id_talle"));
                a.setIva_ar(rs.getString("iva_ar"));
                a.setTemporada_ar(rs.getString("temporada_ar"));
                a.setNombre_cat(rs.getString("nombre_cat"));
                art.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return art;
    }

    public articulos BuscarArticulos(String id) {
        Connection connection = conexion.getConnection();
        articulos a = new articulos();
        try {
            PreparedStatement ps = connection.prepareStatement("Select *  from articulos a INNER JOIN marcas m ON m.id_marca = a.marcas_id_marca INNER JOIN talles t ON a.talles_id_talle = t.id_talle WHERE a.id_articulo = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setId_articulo(rs.getString("id_articulo"));
                a.setCodigo_ar(rs.getString("codigo"));
                a.setBarcode_ar(rs.getString("barcode"));
                a.setId_marca(rs.getString("id_marca"));
                a.setNombre_marca(rs.getString("nombre_marca"));
                a.setPrecio_ar(rs.getString("precio_ar"));
                a.setPrecio_compra_ar(rs.getString("precio_compra_ar"));
                a.setDescripcion_ar(rs.getString("descripcion_ar"));
                a.setId_talle(rs.getString("talles_id_talle"));
                a.setIva_ar(rs.getString("iva_ar"));
                a.setId_categoria(rs.getString("categoria_id_categoria"));
                a.setTemporada_ar(rs.getString("temporada_ar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }

        return a;
    }

    public int AgregarArticulos(articulos art) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO articulos (codigo, barcode, descripcion_ar, precio_ar, precio_compra_ar, iva_ar, temporada_ar, marcas_id_marca, categoria_id_categoria, talles_id_talle) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, art.getCodigo_ar());
            ps.setString(2, IsNull(art.getBarcode_ar()));
            ps.setString(3, art.getDescripcion_ar());
            ps.setString(4, IsInt(art.getPrecio_ar()));
            ps.setString(5, IsInt(art.getPrecio_compra_ar()));
            ps.setString(6, art.getIva_ar());
            ps.setString(7, IsNull(art.getTemporada_ar()));
            ps.setString(8, art.getId_marca());
            ps.setString(9, art.getId_categoria());
            ps.setString(10, art.getId_talle());

            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int ActualizarArticulos(articulos art) {
        Connection connection = conexion.getConnection();
        int r = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE articulos SET codigo = ?, barcode = ?, descripcion_ar = ?, precio_ar = ?, precio_compra_ar = ?, iva_ar = ?, temporada_ar = ?, marcas_id_marca = ?, categoria_id_categoria = ?, talles_id_talle = ? WHERE id_articulo = ?");
            ps.setString(1, art.getCodigo_ar());
            ps.setString(2, IsNull(art.getBarcode_ar()));
            ps.setString(3, art.getDescripcion_ar());
            ps.setString(4, IsInt(art.getPrecio_ar()));
            ps.setString(5, IsInt(art.getPrecio_compra_ar()));
            ps.setString(6, art.getIva_ar());
            ps.setString(7, IsNull(art.getTemporada_ar()));
            ps.setString(8, art.getId_marca());
            ps.setString(9, art.getId_categoria());
            ps.setString(10, art.getId_talle());
            ps.setString(11, art.getId_articulo());
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(connection);
        }
        return r;
    }

    public int GenerarCodigo() {
        Connection con = conexion.getConnection();
        int c = 110001;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id_articulo FROM articulos ORDER BY id_articulo desc LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c += rs.getInt("id_articulo") + 1;
                c = verificarCodigo(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.close(con);
        }
        return c;
    }

    private int verificarCodigo(int c) {
        Connection con = conexion.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT codigo FROM articulos WHERE codigo = ? ");
            ps.setInt(1, c);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c += 1;
                verificarCodigo(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public articulos verificarCodigo_ar(String c) {
        Connection con = conexion.getConnection();
        articulos ar = new articulos();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM articulos a LEFT JOIN talles t ON a.talles_id_talle=t.id_talle WHERE a.codigo = ? OR a.barcode = ?");
            ps.setString(1, c);
            ps.setString(2, c);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ar.setId_articulo(rs.getString("id_articulo"));
                ar.setDescripcion_ar(rs.getString("descripcion_ar"));
                ar.setPrecio_compra_ar(rs.getString("precio_compra_ar"));
                ar.setPrecio_ar(rs.getString("precio_ar"));
                ar.setIva_ar(rs.getString("iva_ar"));
                ar.setTalle_ar(rs.getString("nombre_t"));
                ar.setId_talle(rs.getString("talles_id_talle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOarticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }
}
