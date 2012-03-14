<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>

<h2>Edit City</h2>

<form:form id="cityForm" method="post" action="edit" modelAttribute="editCity">
    <form:hidden path="id" value="${id}" />
    <input type="hidden" id="selectedState" value="${editCity.state.id}" />
    <table>
    <tr>
        <td><label for="txtCity">Name of the City :</label></td>
        <td><form:input path="city" id="txtCity" maxlength="200" /></td> 
    </tr>
    <tr>
        <td><label for="cmbState">State:</label></td>
        <td><form:select path="state" id="cmbState" items="${states}" itemLabel="state" itemValue="id" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Update City"/>
        </td>
    </tr>
</table>    
</form:form>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.5.1.min.js" />">
</script>
<script type="text/javascript">
    $(function(){
        $("#cmbState").val($("#selectedState").attr("value"));
    });
</script>
</body>
</html>
