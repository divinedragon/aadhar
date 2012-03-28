<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Transaction Demo";
    String MENU_NAME  = "";
    String LINK_NAME  = "";
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

<div id="page-heading"><h1>Transaction Demo</h1></div>

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
    <h2>Transaction Details</h2>
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
        <form id="transactionForm" method="get" action="authenticate">
        <table>
            <tr>
                <td>Client Transaction ID :</td>
                <td><input type="text" id="clientTxnId" name="clientTxnId" value="" /></td> 
            </tr>
            <tr>
                <td>Service Provider Name :</td>
                <td><input type="text" id="clientUsername" name="clientUsername" value="iMatrix" /></td> 
            </tr>
            <tr>
                <td>Service Provider Password :</td>
                <td><input type="password" id="clientPassword" name="clientPassword" value="8621ffdbc5698829397d97767ac13db3" /></td> 
            </tr>
            <tr>
                <td>UID :</td>
                <td><input type="text" id="uid" name="uid" value="U00328266854270" /></td> 
            </tr>
            <tr>
                <td>Amount :</td>
                <td><input type="text" id="amount" name="amount" value="2000.00" /></td> 
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" class="form-submit" />
                    <input type="reset" value="Reset" class="form-reset"/>
                </td>
            </tr>
        </table>
        </form>
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
<script type="text/javascript">
    $(function(){
    	$("#clientTxnId").val("T" + parseInt(Math.random() * 100000, 10));
    });
</script>

<%@include file="../common/footer.jsp" %>