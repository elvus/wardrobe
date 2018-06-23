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
import paquete.dao.DAOempleado;
import paquete.modelo.contactos;
import paquete.modelo.empleados;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "empleadoControlador", urlPatterns = {"/empleadoControlador"})
public class empleadoControlador extends HttpServlet {

    private DAOempleado dao;

    public empleadoControlador() {
        dao = new DAOempleado();
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
                ArrayList<empleados> emp = dao.ListarEmpleados();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", emp);
                } catch (JSONException ex) {
                    Logger.getLogger(empleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);
                break;
            }
            case "sinusuario": {
                writer.println(gson.toJson(dao.SinUsuario()));
                break;
            }
            case "buscar": {
                writer.println(gson.toJson(dao.BuscarEmpleados(request.getParameter("id_empleado"))));
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
        PrintWriter writer = response.getWriter();
        int accion = Integer.valueOf(request.getParameter("accion"));
        int resp = 0;
        if (accion == 0) {
            empleados emp = new empleados();
            emp.setNombre_emp(request.getParameter("nombre_emp"));
            emp.setApellido_emp(request.getParameter("apellido_emp"));
            emp.setNdocumento_emp(request.getParameter("ndocumento_emp"));
            emp.setFecha_ingreso_emp(request.getParameter("fecha_ingreso_emp"));
            emp.setFnac_emp(request.getParameter("fnac_emp"));
            emp.setId_cargo(request.getParameter("id_cargo"));
            emp.setId_pais(request.getParameter("id_nacionalidad"));
            emp.setId_ciudad(request.getParameter("id_ciudad"));
            int r = dao.AgregarEmpleados(emp);
            if (r != 0) {
                empleados con = new empleados();
                con.setCelular_cont(request.getParameter("celular_cont"));
                con.setTelefono_cont(request.getParameter("telefono_cont"));
                con.setCorreo_cont(request.getParameter("correo_cont"));
                con.setPrincipal_cont(request.getParameter("principal_cont"));
                con.setId_empleado(String.valueOf(r));
                resp = dao.AgregarContacto(con);
            }
            writer.println(resp);
        } else if (accion == 1) {
            empleados emp = new empleados();
            emp.setId_empleado(request.getParameter("id_empleado"));
            emp.setNombre_emp(request.getParameter("nombre_emp"));
            emp.setApellido_emp(request.getParameter("apellido_emp"));
            emp.setNdocumento_emp(request.getParameter("ndocumento_emp"));
            emp.setFecha_ingreso_emp(request.getParameter("fecha_ingreso_emp"));
            emp.setFnac_emp(request.getParameter("fnac_emp"));
            emp.setId_cargo(request.getParameter("id_cargo"));
            emp.setId_pais(request.getParameter("id_nacionalidad"));
            emp.setId_ciudad(request.getParameter("id_ciudad"));
            emp.setCelular_cont(request.getParameter("celular_cont"));
            emp.setTelefono_cont(request.getParameter("telefono_cont"));
            emp.setCorreo_cont(request.getParameter("correo_cont"));
            writer.println(dao.ActualizarEmpleado(emp));
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
