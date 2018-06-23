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
import paquete.dao.DAOcategoria;
import paquete.modelo.categorias;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "categoriaControlador", urlPatterns = {"/categoriaControlador"})
public class categoriaControlador extends HttpServlet {

    DAOcategoria dao;

    public categoriaControlador() {
        dao = new DAOcategoria();
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
        Gson gson = new Gson();
        String accion = request.getParameter("accion");
        String id_cat = request.getParameter("id_cat");
        switch (accion) {
            case "listar":
                ArrayList<categorias> c = dao.ListarCategorias();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", c);
                } catch (JSONException ex) {
                    Logger.getLogger(categoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            case "select":
                writer.println(gson.toJson(dao.ListarCategorias()));
                break;
            case "buscar":
                System.out.println(id_cat);
                writer.println(gson.toJson(dao.buscarcategorias(id_cat)));
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
        PrintWriter writer = response.getWriter();
        processRequest(request, response);
        int accion = Integer.valueOf(request.getParameter("accion"));
        switch (accion) {
            case 0: {
                categorias cat = new categorias();
                cat.setDescripcion_cat(request.getParameter("descripcion_cat"));
                cat.setNombre_cat(request.getParameter("nombre_cat"));
                writer.println(dao.AgregarCategorias(cat));
                break;
            }
            case 1: {
                categorias cat = new categorias();
                cat.setId_categoria(request.getParameter("id_cat"));
                cat.setNombre_cat(request.getParameter("nombre_cat"));
                cat.setDescripcion_cat(request.getParameter("descripcion_cat"));
                writer.println(dao.ActualizarCategorias(cat));
                break;
            }
            case 2: {
                String id = request.getParameter("id_cat");
                writer.println(dao.EliminarCategoria(id));
            }
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
