/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.controlador;

import com.google.gson.Gson;
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
import paquete.dao.DAOdepartamentos;
import paquete.modelo.departamentos;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "departamentosControlador", urlPatterns = {"/departamentosControlador"})
public class departamentosControlador extends HttpServlet {

    private DAOdepartamentos dao;

    public departamentosControlador() {
        super();
        dao = new DAOdepartamentos();
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

        String accion = request.getParameter("accion");
        String id = request.getParameter("id");
        Gson gson = new Gson();
        switch (accion) {
            case "listar":
                ArrayList<departamentos> emp = dao.ListarDepartamentos();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", emp);
                } catch (JSONException ex) {
                    Logger.getLogger(empleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            case "select":
                writer.println(gson.toJson(dao.ListarDepartamentos()));
                break;
            case "buscar":
                writer.println(gson.toJson(dao.BuscarDepartamentos(id)));
                break;
            case "eliminar":
                writer.println(dao.EliminarDepartamentos(id));
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
        departamentos dptos = new departamentos();
        dptos.setId_departamento(request.getParameter("id_departamento"));
        dptos.setNombre_dep(request.getParameter("nombre_dep"));
        if (accion == 0) {
            writer.println(dao.AgregarDepartamentos(dptos));
        } else if (accion == 1) {
            writer.println(dao.ActualizarDepartamentos(dptos));
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
