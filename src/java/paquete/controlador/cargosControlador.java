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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOcargos;
import paquete.modelo.cargos;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "cargosControlador", urlPatterns = {"/cargosControlador"})
public class cargosControlador extends HttpServlet {
    
    private DAOcargos dao;
    
    public cargosControlador() {
        super();
        dao = new DAOcargos();
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
        response.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        Gson gson = new GsonBuilder().create();
        String accion = request.getParameter("accion");
        
        if (accion.equalsIgnoreCase("listar")) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("data", dao.ListarCargos());
            } catch (JSONException ex) {
                Logger.getLogger(cargosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.print(obj);
        } else if (accion.equalsIgnoreCase("0")) {
            cargos c = new cargos();
            c.setNombre_cargo(request.getParameter("nombre_cargo"));
            writer.println(dao.AgregarCargos(c));
        } else if (accion.equalsIgnoreCase("buscar")) {
            writer.println(gson.toJson(dao.BuscarCargo(request.getParameter("id_cargo"))));
        } else if (accion.equalsIgnoreCase("1")) {
            cargos c = new cargos();
            c.setId_cargo(request.getParameter("id_cargo"));
            c.setNombre_cargo(request.getParameter("nombre_cargo"));
            writer.println(dao.ActualizarCargos(c));
        } else if (accion.equalsIgnoreCase("select")) {
            writer.println(gson.toJson(dao.ListarCargos()));
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
