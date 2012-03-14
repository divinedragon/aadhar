<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>

<h2>Add New City</h2>

<form:form id="cityForm" method="post" action="create" modelAttribute="newCity">

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
            <input type="submit" value="Add City"/>
        </td>
    </tr>
</table>    
</form:form>

</body>
</html>
