/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOarticulos;
import paquete.modelo.articulos;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "articuloControlador", urlPatterns = {"/articuloControlador"})
public class articuloControlador extends HttpServlet {

    private DAOarticulos dao;

    public articuloControlador() {
        super();
        dao = new DAOarticulos();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        Gson gson = new GsonBuilder().create();
        String accion = request.getParameter("accion");
        String id = request.getParameter("id_articulo");
        switch (accion) {
            case "listar":
                ArrayList<articulos> art = dao.ListarArticulos();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", art);
                } catch (JSONException ex) {
                    Logger.getLogger(empleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            case "buscar":
                writer.println(gson.toJson(dao.BuscarArticulos(id)));
                break;
            case "completar":
                writer.println(gson.toJson(dao.ListarArticulos()));
                break;
            case "generar":
                writer.println(dao.GenerarCodigo());
                break;
            case "verificar":
                writer.println(gson.toJson(dao.verificarCodigo_ar(request.getParameter("cod"))));
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter writer = response.getWriter();
        int accion = Integer.valueOf(request.getParameter("accion"));

        articulos art = new articulos();
        art.setCodigo_ar(request.getParameter("codigo_ar"));
        art.setBarcode_ar(request.getParameter("barcode_ar"));
        art.setDescripcion_ar(request.getParameter("descripcion_ar"));
        art.setId_marca(request.getParameter("id_marca"));
        art.setPrecio_ar(request.getParameter("precio_ar"));
        art.setPrecio_compra_ar(request.getParameter("precio_compra_ar"));
        art.setIva_ar(request.getParameter("iva_ar"));
        art.setTemporada_ar(request.getParameter("temporada_ar"));
        art.setId_talle(request.getParameter("talle_ar"));
        art.setId_categoria(request.getParameter("id_categoria"));
        switch (accion) {
            case 0:
                writer.println(dao.AgregarArticulos(art));
                break;
            case 1:
                art.setId_articulo(request.getParameter("id_articulo"));
                writer.println(dao.ActualizarArticulos(art));
                break;

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
