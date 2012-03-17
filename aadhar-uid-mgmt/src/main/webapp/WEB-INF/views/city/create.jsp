<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="../css/jquery.validate.css" type="text/css" media="screen" title="default" />
    <link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" title="default" />
    <style type="text/css">
        body {background:#333333;}
        form, form fieldset { width: 98%; }
        fieldset, table td { padding: 5px;}
        label { color:white; font-weight:bold; font-size:14px;  }
        option { padding:0px }
    </style>
</head>
<body>

<form:form id="cityForm" method="post" action="create" modelAttribute="newCity">
    <fieldset>
    <legend>Add New City</legend>
    <table>
    <tr>
        <td><label for="txtCity">Name of the City :</label></td>
        <td><form:input path="city" id="txtCity" maxlength="200" size="15" /></td> 
    </tr>
    <tr>
        <td><label for="cmbState">State:</label></td>
        <td><form:select path="state" id="cmbState" items="${states}" itemLabel="state" itemValue="id" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add City" class="form-submit" />
            <input type="reset" value="Canel" class="form-reset"/>
        </td>
    </tr>
    </table>
    </fieldset>  
</form:form>
<script src="../js/jquery/jquery-1.5.1.min.js" type="text/javascript"></script>
<script src="../js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="../js/jquery/jquery.validation.functions.js" type="text/javascript"></script>
<script type="text/javascript">
    /* <![CDATA[ */
    jQuery(function(){
        jQuery("#txtCity").validate({
            expression: "if (VAL.match(/^[A-Za-z0-9\s]{3,100}$/)) return true; else return false;",
            message: "Only Alphabets and numbers allowed with minimum 3 characters"
        });
        jQuery("#ValidSelection").validate({
            expression: "if (VAL != '0') return true; else return false;",
            message: "Please make a selection"
        });

        jQuery('#cityForm').validated(function(){
            alert("Use this call to make AJAX submissions.");
        });
    });
    /* ]]> */
</script>
</body>
</html>
