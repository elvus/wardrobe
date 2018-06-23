/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static extras.extras.fechaActual;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOnotadecredito;
import paquete.modelo.ventas;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "notacreditoControlador", urlPatterns = {"/notacreditoControlador"})
public class notacreditoControlador extends HttpServlet {

    DAOnotadecredito dao;

    public notacreditoControlador() {
        dao = new DAOnotadecredito();
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
                obj.put("data", dao.ListarNotasCredito());
            } catch (JSONException ex) {
                Logger.getLogger(notacreditoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.println(obj);
        } else if (accion.equalsIgnoreCase("0")) {
            ventas v = new ventas();
            int r = 0, id_nc = 0;
            v.setId_cliente(request.getParameter("id_cliente"));
            v.setTipo_ven(request.getParameter("tipo_ven"));
            v.setTotal_ven(request.getParameter("total_ven"));
            v.setTotal_iva_ven(request.getParameter("total_iva_ven"));
            v.setFecha_ven(fechaActual());
            v.setTipo_ven(request.getParameter("tipo_ven"));
            v.setNro_factura(request.getParameter("id_venta"));
            id_nc = dao.AgregarNotasdeCredito(v);
            if (id_nc != 0) {
                try {
                    JSONArray array = new JSONArray(request.getParameter("detalle"));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        ventas det = new ventas();
                        det.setId_venta(String.valueOf(id_nc));
                        det.setId_articulo(obj.getString("id_articulo"));
                        det.setTalle_ven(obj.getString("talle_ven"));
                        det.setCantidad_ven(obj.getString("cantidad_ven"));
                        det.setPrecio_ven(obj.getString("precio_ven"));
                        det.setIva(obj.getString("iva_ven"));
                        det.setSubtotal_ven(obj.getString("subtotal_ven"));
                        r = dao.AgregarDetalleCredito(det);
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(ventasControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            writer.println(r);
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
