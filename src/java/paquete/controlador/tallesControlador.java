/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOtalles;
import paquete.modelo.talles;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "tallesControlador", urlPatterns = {"/tallesControlador"})
public class tallesControlador extends HttpServlet {

    private DAOtalles dao;

    public tallesControlador() {
        dao = new DAOtalles();
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
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("data", dao.ListarTalles());
            } catch (JSONException ex) {
                Logger.getLogger(tallesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.println(obj);
        } else if (accion.equalsIgnoreCase("0")) {
            talles t = new talles();
            t.setNombre_t(request.getParameter("nombre_t"));
            t.setDescripcion_t(request.getParameter("descripcion_t"));
            writer.println(dao.AgregarTalles(t));
        } else if (accion.equalsIgnoreCase("select")) {
            writer.println(gson.toJson(dao.ListarTalles()));
        } else if (accion.equalsIgnoreCase("buscar")) {
            writer.println(gson.toJson(dao.BuscarTalles(request.getParameter("id_talle"))));
        } else if (accion.equalsIgnoreCase("1")) {
            talles t = new talles();
            t.setId_talle(request.getParameter("id_talle"));
            t.setNombre_t(request.getParameter("nombre_t"));
            t.setDescripcion_t(request.getParameter("descripcion_t"));
            writer.println(dao.ActualizarTalles(t));
        }
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
