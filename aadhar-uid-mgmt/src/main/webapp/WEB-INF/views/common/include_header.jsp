<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
    /* Check which type of user is this - Admin or general */
    /* We will include the appropriate header file */
    
    Object springSecurityContext = session.getValue("SPRING_SECURITY_CONTEXT");

    if (springSecurityContext == null) {
        return ;
    }

    String strSecurityContext = springSecurityContext.toString();

    if (strSecurityContext != null && strSecurityContext.contains("ROLE_OFFICIAL")) {
%>
        <%@include file="header_admin.jsp" %>
        <%@include file="menu_admin.jsp" %>
<%
    }
%>

