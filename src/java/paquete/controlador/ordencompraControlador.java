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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paquete.dao.DAOordencompra;
import paquete.modelo.ordendecompra;

/**
 *
 * @author Elvis
 */
@WebServlet(name = "ordencompraControlador", urlPatterns = {"/ordencompraControlador"})
public class ordencompraControlador extends HttpServlet {

    private DAOordencompra dao;

    public ordencompraControlador() {
        super();
        dao = new DAOordencompra();
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
        String id = request.getParameter("id");

        switch (accion) {
            case "listar": {
                ArrayList<ordendecompra> oc = dao.ListarOrdenes();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", oc);
                } catch (JSONException ex) {
                    Logger.getLogger(ciudadControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                writer.println(obj);

                break;
            }
            case "buscarCab": {
                writer.println(gson.toJson(dao.BuscarOrdenes(request.getParameter("id_orden"))));
                break;
            }
            case "buscarDet": {
                writer.println(gson.toJson(dao.BuscarDetalleOrd(request.getParameter("id_orden"))));
                break;
            }
            case "aprobar": {
                writer.println(dao.AprobarOrden(request.getParameter("id_orden"), "1"));
                break;
            }
            case "recibir": {
                writer.println(dao.RecibirOrden(request.getParameter("id_orden")));
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
        int r = 0;
        String id = request.getParameter("id_orden");
        ordendecompra ord = new ordendecompra();
        ord.setComentario_ord(request.getParameter("comentario_ord"));
        ord.setFecha_ord(request.getParameter("fecha_ord"));
        ord.setTotal_ord(request.getParameter("total_ord"));
        ord.setId_proveedor(request.getParameter("id_proveedor"));
        switch (accion) {
            case 0:
                ord.setId_orden_compra(String.valueOf(dao.AgregarOrdenes(ord)));
                if (!ord.getId_orden_compra().isEmpty()) {
                    try {
                        JSONArray array = new JSONArray(request.getParameter("detalle_ord"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            ordendecompra det = new ordendecompra();
                            det.setId_orden_compra(ord.getId_orden_compra());
                            det.setId_articulo(obj.getString("id_articulo"));
                            det.setPrecio_ord(obj.getString("precio_ord"));
                            det.setIva_ord(obj.getString("iva_ord"));
                            det.setCantidad_ord(obj.getString("cantidad_ord"));
                            det.setCodigo_ar_ord(obj.getString("codigo_ar_ord"));
                            r = dao.AgregarArticulosOrdenardos(det);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(ordencompraControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                writer.println(r);
                break;
            case 1:
                ord.setId_orden_compra(id);
                r = dao.ActualizarOrden(ord);
                if (r != 0) {
                    r = dao.EliminarDetArtOrd(id);
                    if (r != 0) {
                        try {
                            JSONArray array = new JSONArray(request.getParameter("detalle_ord"));
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                ordendecompra det = new ordendecompra();
                                det.setId_orden_compra(ord.getId_orden_compra());
                                det.setId_articulo(obj.getString("id_articulo"));
                                det.setPrecio_ord(obj.getString("precio_ord"));
                                det.setIva_ord(obj.getString("iva_ord"));
                                det.setCantidad_ord(obj.getString("cantidad_ord"));
                                det.setCodigo_ar_ord(obj.getString("codigo_ar_ord"));
                                r = dao.AgregarArticulosOrdenardos(det);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(ordencompraControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                writer.println(r);
                break;
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
