<%-- 
    Document   : session
    Created on : 17/07/2017, 03:10:14 PM
    Author     : Elvis
--%>

<%@page import="paquete.conexion.conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1 
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
    response.setDateHeader("Expires", 0);
    HttpSession ok = request.getSession();
    String usuario = (String) ok.getAttribute("user");
    if ((conexion.user == null || conexion.user == "") || (conexion.pass == "" || conexion.pass == null)) {
        response.sendRedirect("index.jsp");
    }
    if (usuario == null) {
        response.sendRedirect("index.jsp");
    }
%>