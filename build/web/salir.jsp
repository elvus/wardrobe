<%-- 
    Document   : salir
    Created on : 24/06/2017, 08:54:56 AM
    Author     : Elvis
--%>

<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control","no-store"); //HTTP 1.1 
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 
    response.setDateHeader("Expires", 0);
    HttpSession ok = request.getSession();
    ok.invalidate();
    response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    </body>
</html>
