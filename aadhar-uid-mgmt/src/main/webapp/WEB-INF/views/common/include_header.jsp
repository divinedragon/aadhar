<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    /* Check which type of user is this - Admin or general */
    /* We will include the appropriate header file */
    
%>
<%@include file="header_admin.jsp" %>
<%@include file="menu_admin.jsp" %>