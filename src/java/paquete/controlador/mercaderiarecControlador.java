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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOmercaderiasrecibida;
import paquete.modelo.mercaderiasrecibidas;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "mercaderiarecControlador", urlPatterns = {"/mercaderiarecControlador"})
public class mercaderiarecControlador extends HttpServlet {

    private DAOmercaderiasrecibida dao;

    public mercaderiarecControlador() {
        super();
        dao = new DAOmercaderiasrecibida();
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
        String id = request.getParameter("id");
        int r = 0;

        if (accion.equalsIgnoreCase("listar")) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("data", dao.ListarMercaderiasRecibidas());
            } catch (JSONException ex) {
                Logger.getLogger(mercaderiarecControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.println(obj);
        } else if (accion.equalsIgnoreCase("0")) {
            mercaderiasrecibidas mr = new mercaderiasrecibidas();
            mr.setNrofactura_mr(request.getParameter("nrofactura_mr"));
            mr.setFecha_mr(request.getParameter("fecha_mr"));
            mr.setComentario_mr(request.getParameter("cometario_mr"));
            mr.setTotal_mr(request.getParameter("total_mr"));
            mr.setTotal_iva_mr(request.getParameter("total_iva_mr"));
            mr.setIva_rec(request.getParameter("iva_mr"));
            mr.setId_proveedor(request.getParameter("id_proveedor"));
            mr.setId_mercaderia_recibida(String.valueOf(dao.AgregarMercaderiasRecibidas(mr)));
            if (!mr.getId_mercaderia_recibida().isEmpty()) {
                try {
                    JSONArray array = new JSONArray(request.getParameter("detalle_mr"));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        mercaderiasrecibidas det = new mercaderiasrecibidas();
                        det.setId_articulo(obj.getString("id_articulo"));
                        det.setId_mercaderia_recibida(mr.getId_mercaderia_recibida());
                        det.setCantidad_rec(obj.getString("cantidad_mr"));
                        det.setTalle_mr(obj.getString("talle_mr"));
                        det.setPrecio_unitario_rec(obj.getString("precio_unitario_mr"));
                        det.setSubtotal_rec(obj.getString("subtotal_mr"));
                        det.setIva_rec("0");
                        r = dao.AgregarArticulosRecibidos(det);
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(mercaderiarecControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            writer.println(r);
        } else if (accion.equalsIgnoreCase("buscarCab")) {
            writer.println(gson.toJson(dao.BuscarMercaderiasRecibidas(request.getParameter("id_mercaderia_recibida"))));
        } else if (accion.equalsIgnoreCase("buscarDet")) {
            writer.println(gson.toJson(dao.BuscarArticulosRecibidos(request.getParameter("id_mercaderia_recibida"))));
        } else if (accion.equalsIgnoreCase("1")) {
            mercaderiasrecibidas mr = new mercaderiasrecibidas();
            mr.setId_mercaderia_recibida(request.getParameter("id_mercaderia_recibida"));
            mr.setNrofactura_mr(request.getParameter("nrofactura_mr"));
            mr.setFecha_mr(request.getParameter("fecha_mr"));
            mr.setComentario_mr(request.getParameter("cometario_mr"));
            mr.setTotal_mr(request.getParameter("total_mr"));
            mr.setTotal_iva_mr(request.getParameter("total_iva_mr"));
            mr.setId_proveedor(request.getParameter("id_proveedor"));
            if (dao.ActualizarMercaderiasRecibidas(mr) == 1) {
                r = dao.EliminarDetalleMR(request.getParameter("id_mercaderia_recibida"));
                if (r > 0) {
                    try {
                        JSONArray array = new JSONArray(request.getParameter("detalle_mr"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            mercaderiasrecibidas det = new mercaderiasrecibidas();
                            det.setId_articulo(obj.getString("id_articulo"));
                            det.setId_mercaderia_recibida(mr.getId_mercaderia_recibida());
                            det.setCantidad_rec(obj.getString("cantidad_mr"));
                            det.setTalle_mr(obj.getString("talle_mr"));
                            det.setPrecio_unitario_rec(obj.getString("precio_unitario_mr"));
                            det.setSubtotal_rec(obj.getString("subtotal_mr"));
                            det.setIva_rec(obj.getString("iva_mr"));
                            r = dao.AgregarArticulosRecibidos(det);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(mercaderiarecControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
