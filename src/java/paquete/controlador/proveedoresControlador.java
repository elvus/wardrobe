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
import paquete.dao.DAOproveedores;
import paquete.modelo.proveedores;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "proveedoresControlador", urlPatterns = {"/proveedoresControlador"})
public class proveedoresControlador extends HttpServlet {
    
    private DAOproveedores dao;
    
    public proveedoresControlador() {
        super();
        dao = new DAOproveedores();
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
        switch (accion) {
            case "listar": {
                ArrayList<proveedores> cli = dao.ListarProveedores();
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
                writer.println(gson.toJson(dao.ListarProveedores()));
                break;
            }
            case "buscar": {
                writer.println(gson.toJson(dao.BuscarProveedores(request.getParameter("id_proveedor"))));
                break;
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
        processRequest(request, response);
        PrintWriter writer = response.getWriter();
        int accion = Integer.valueOf(request.getParameter("accion"));
        proveedores pro = new proveedores();
        if (accion == 0) {
            pro.setRuc_pro(request.getParameter("ruc_pro"));
            pro.setDv_pro(request.getParameter("dv_pro"));
            pro.setRepresentante_pro(request.getParameter("representante_pro"));
            pro.setNombre_pro(request.getParameter("nombre_pro"));
            pro.setDireccion_pro(request.getParameter("direccion_pro"));
            pro.setTelefono_pro(request.getParameter("telefono_pro"));
            pro.setCelular_pro(request.getParameter("celular_pro"));
            pro.setCorreo_pro(request.getParameter("correo_pro"));
            pro.setId_ciudad(request.getParameter("id_ciudad"));
            writer.println(dao.AgregarProveedores(pro));
        } else if (accion == 1) {
            pro.setId_proveedor(request.getParameter("id_proveedor"));
            pro.setRuc_pro(request.getParameter("ruc_pro"));
            pro.setDv_pro(request.getParameter("dv_pro"));
            pro.setRepresentante_pro(request.getParameter("representante_pro"));
            pro.setNombre_pro(request.getParameter("nombre_pro"));
            pro.setDireccion_pro(request.getParameter("direccion_pro"));
            pro.setTelefono_pro(request.getParameter("telefono_pro"));
            pro.setCelular_pro(request.getParameter("celular_pro"));
            pro.setCorreo_pro(request.getParameter("correo_pro"));
            pro.setId_ciudad(request.getParameter("id_ciudad"));
            writer.println(dao.ActualizarProveedores(pro));
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
