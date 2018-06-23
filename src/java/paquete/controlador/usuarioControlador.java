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
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOusuario;
import paquete.modelo.usuarios;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "usuarioControlador", urlPatterns = {"/usuarioControlador"})
public class usuarioControlador extends HttpServlet {
    
    private DAOusuario dao;
    
    public usuarioControlador() {
        dao = new DAOusuario();
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
        HttpSession ok = request.getSession();
        String alias = String.valueOf(ok.getAttribute("user"));
        String accion = request.getParameter("accion");
        Gson gson = new GsonBuilder().create();
        System.out.println(alias);
        switch (accion) {
            case "inicio":
                writer.println(gson.toJson(dao.BuscarUsuario(alias)));
                break;
            case "listar":
                ArrayList<usuarios> users = dao.ListarUsuarios();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", users);
                } catch (JSONException ex) {
                    Logger.getLogger(usuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            case "verificaAlias":
                writer.println(dao.VerificarAlias_usu(request.getParameter("alias_usu")));
                break;
            case "permisos":
                writer.println(gson.toJson(dao.BuscarPermisosxUsuario(request.getParameter("id_usuario"))));
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
        int r = 0;
        String accion = request.getParameter("accion");
        if (accion.equals("0")) {
            usuarios user = new usuarios();
            user.setAlias(request.getParameter("usuario"));
            user.setPassword(request.getParameter("password"));
            user.setId_empleado(request.getParameter("id_empleado"));
            r = dao.AgregarUsuarios(user);
        }
        writer.println(r);
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
