<%-- 
    Document   : redirect
    Created on : Jan 20, 2015, 8:16:31 PM
    Author     : kiwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Constants" class="ua.aits.Carpath.functions.Constants" scope="session"/>
<%
    String redirectURL = Constants.URL+"ua/index";
    response.sendRedirect(redirectURL);
%>