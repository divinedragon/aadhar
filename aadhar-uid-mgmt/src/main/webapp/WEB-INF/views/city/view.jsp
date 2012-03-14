<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>

<h2>View City</h2>

<table>
    <tr>
        <td>ID :</td>
        <td>${dbCity.id}</td> 
    </tr>
    <tr>
        <td>Name of the City :</td>
        <td>${dbCity.city}</td> 
    </tr>
    <tr>
        <td>State:</td>
        <td>${dbCity.state.state}</td>
    </tr>
</table>
<a href="edit?id=${dbCity.id}">Edit</a> | <a href="delete?id=${dbCity.id}">Delete</a>
</body>
</html>
