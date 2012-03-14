<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    /* Check which type of user is this - Admin or general */
    /* We will include the appropriate header file */
    
%>
<%
    String PAGE_TITLE = ":: Aadhar :: Manage City, District and State";
%>
<%@include file="../common/include_header.jsp" %>


<div id="page-heading"><h1>Manage City, District and State</h1></div>


<%@include file="../common/footer.jsp" %>
