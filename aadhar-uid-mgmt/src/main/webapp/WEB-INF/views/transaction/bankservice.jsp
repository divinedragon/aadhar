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

<div id="page-heading"><h1>Bank Authentication Page</h1></div>

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
    <h2>Enter Bank Account Password</h2>
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
        <form id="bankTransactionForm" method="get" action="bankresponse">
        <input type="hidden" name="txnId" value="${txnId}" />
        <input type="hidden" name="status" value="SUCCESS" />
        <input type="hidden" id="bankTxnId" name="bankTxnId" value="" />
        <table>
            <tr>
                <td>Bank Transaction ID :</td>
                <td id="displayBankTxnId">&nbsp;</td> 
            </tr>
            <tr>
                <td>Transaction Amount :</td>
                <td>${amount}</td> 
            </tr>
            <tr>
                <td>Service Provider Name :</td>
                <td>${serviceProvider.name}</td> 
            </tr>
            <tr>
                <td>Service Provider Account Number :</td>
                <td>${serviceProvider.accountNumber}</td> 
            </tr>
            <tr>
                <td>Bank IFSC Code :</td>
                <td>${serviceProvider.bankIFSCCode}</td> 
            </tr>
            <tr>
                <td>Citizen Account Number :</td>
                <td>${citizenAcccountNumber}</td> 
            </tr>
            <tr>
                <td>UID :</td>
                <td>${uid}</td> 
            </tr>
            <tr>
                <td>Account Password :</td>
                <td><input type="password" id="bankPassword" name="bankPassword" value="password#123" /></td> 
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
        $("#bankTxnId").val("BK" + parseInt(Math.random() * 100000, 10));
        $("#displayBankTxnId").html($("#bankTxnId").val());
    });
</script>

<%@include file="../common/footer.jsp" %>