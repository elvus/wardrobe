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
import paquete.dao.DAOciudad;
import paquete.modelo.ciudades;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "ciudadControlador", urlPatterns = {"/ciudadControlador"})
public class ciudadControlador extends HttpServlet {

    private DAOciudad dao;

    public ciudadControlador() {
        super();
        dao = new DAOciudad();
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
        response.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        Gson gson = new GsonBuilder().create();
        String accion = request.getParameter("accion");
        String id = request.getParameter("id");
        switch (accion) {
            case "listar":
                ArrayList<ciudades> city = dao.ListarCiudades();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", city);
                } catch (JSONException ex) {
                    Logger.getLogger(ciudadControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            case "select":
                ArrayList<ciudades> ciu = dao.ListarCiudades();
                writer.println(gson.toJson(ciu));
                break;
            case "buscar":
                writer.println(gson.toJson(dao.BuscarCiudades(id)));
                break;
            case "eliminar":
                writer.println(dao.EliminarCiudad(id));
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
        String id = request.getParameter("id");
        ciudades c = new ciudades();
        c.setId_ciudad(id);
        c.setNombre_ciudad(request.getParameter("nombre_ciudad"));
        c.setId_departamento(request.getParameter("id_departamento"));
        if (accion == 0) {
            writer.println(dao.AgregarCiudad(c));
        } else if (accion == 1) {
            writer.println(dao.ActualizarCiudad(c));
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
