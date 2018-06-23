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
import paquete.dao.DAOclientes;
import paquete.modelo.clientes;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "clientesControlador", urlPatterns = {"/clientesControlador"})
public class clientesControlador extends HttpServlet {

    private DAOclientes dao;

    public clientesControlador() {
        super();
        dao = new DAOclientes();
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
        switch (accion) {
            case "listar": {
                ArrayList<clientes> cli = dao.ListarClientes();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", cli);
                } catch (JSONException ex) {
                    Logger.getLogger(empleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            }
            case "completar": {
                writer.println(gson.toJson(dao.ListarClientes()));
                break;
            }

            case "buscar": {
                writer.println(gson.toJson(dao.BuscarClientes(request.getParameter("id_cliente"))));
            }
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
        response.setCharacterEncoding("UTF8");
        PrintWriter writer = response.getWriter();
        String accion = request.getParameter("accion");
        int r = 0;

        if ("0".equals(accion)) {
            clientes cli = new clientes();
            cli.setNombre_cli(request.getParameter("nombre_cli"));
            cli.setDocumento_cli(request.getParameter("ndocumento_cli"));
            cli.setDv_cli(request.getParameter("dv_cli"));
            cli.setTelefono_cli(request.getParameter("telefono_cli"));
            cli.setCelular_cli(request.getParameter("celular_cli"));
            cli.setCorreo_cli(request.getParameter("correo_cli"));
            cli.setDireccion_cli(request.getParameter("direccion_cli"));
            cli.setId_ciudad(request.getParameter("id_ciudad"));
            r = dao.AgregarClientes(cli);
            writer.println(r);
        }
        if (accion.equalsIgnoreCase("1")) {
            clientes cli = new clientes();
            cli.setId_cliente(request.getParameter("id_cliente"));
            cli.setNombre_cli(request.getParameter("nombre_cli"));
            cli.setDocumento_cli(request.getParameter("ndocumento_cli"));
            cli.setDv_cli(request.getParameter("dv_cli"));
            cli.setTelefono_cli(request.getParameter("telefono_cli"));
            cli.setCelular_cli(request.getParameter("celular_cli"));
            cli.setCorreo_cli(request.getParameter("correo_cli"));
            cli.setDireccion_cli(request.getParameter("direccion_cli"));
            cli.setId_ciudad(request.getParameter("id_ciudad"));
            r = dao.ActualizarClientes(cli);
            writer.println(r);
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
