<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Add New Bank";
    String MENU_NAME  = "Banks";
    String LINK_NAME  = "Register New Bank";
%>
<%@include file="../common/include_header.jsp" %>

<link rel="stylesheet" href="../css/jquery.validate.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" title="default" />
<style type="text/css">
    table td { padding: 5px;}
    label { color:black; font-weight:bold; font-size:14px;  }
    span.ValidationErrors { display: inline-block; width:400px; }
</style>
<!-- IE6 "fix" for the close png image -->
<!--[if lt IE 7]>
<link type='text/css' href='../css/basic_ie.css' rel='stylesheet' media='screen' />
<![endif]-->

<div id="page-heading"><h1>Add New Bank</h1></div>

<table border="0" width="100%" cellpadding="0" cellspacing="0" id="content-table">
<tr>
    <th rowspan="3" class="sized"><img src="../images/shared/side_shadowleft.jpg" width="20" height="300" alt="" /></th>
    <th class="topleft"></th>
    <td id="tbl-border-top">&nbsp;</td>
    <th class="topright"></th>
    <th rowspan="3" class="sized"><img src="../images/shared/side_shadowright.jpg" width="20" height="300" alt="" /></th>
</tr>
<tr>
    <td id="tbl-border-left"></td>
    <td>
    <!--  start content-table-inner -->
    <div id="content-table-inner">
    <h2>Enter new bank details</h2>
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
        <form:form id="bankForm" method="post" action="create" modelAttribute="newBank">
            <table>
		    <tr>
		        <td><label for="txtName">Bank Name :</label></td>
		        <td><form:input path="name" id="txtName" maxlength="200" /></td> 
		    </tr>
		    <tr>
                <td><label for="txtUrl">URL :</label></td>
                <td><form:input path="url" id="txtUrl" maxlength="200" /></td> 
            </tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Add Bank" class="form-submit" />
		            <input type="reset" value="Reset" class="form-reset"/>
		        </td>
		    </tr>
		    </table>
		</form:form>
		<a href="list">List all Banks</a>
    </td>
    </tr>
    <tr>
        <td><img src="../images/shared/blank.gif" width="695" height="1" alt="blank" /></td>
        <td></td>
    </tr>
    </table>
 
    <div class="clear"></div>
 

    </div>
    <!--  end content-table-inner  -->
    </td>
    <td id="tbl-border-right"></td>
</tr>
<tr>
    <th class="sized bottomleft"></th>
    <td id="tbl-border-bottom">&nbsp;</td>
    <th class="sized bottomright"></th>
</tr>
</table>

<script src="../js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="../js/jquery/jquery.validation.functions.js" type="text/javascript"></script>
<script type="text/javascript">
    /* <![CDATA[ */
    jQuery(function(){
        jQuery("#txtName").validate({
            expression: "if (VAL.match(/^[A-Za-z\s]{3,100}$/)) return true; else return false;",
            message: "Only Alphabets allowed with minimum 3 characters"
        });
        jQuery("#txtUrl").validate({
        	expression: "if (VAL.length > 5 && VAL) return true; else return false;",
            message: "Please provide a valid url"
        });

        jQuery('#bankForm').validated(function(){
            $('#bankForm').submit();
        });
    });
    /* ]]> */
</script>

<%@include file="../common/footer.jsp" %>