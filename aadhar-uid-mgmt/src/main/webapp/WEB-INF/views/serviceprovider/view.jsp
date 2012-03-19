<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: View Service Provider";
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

<div id="page-heading"><h1>View Service Provider Details - ID #${dbServiceProvider.id}</h1></div>

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
    <h2>Service Provider Details</h2>
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
        <table>
            <tr>
                <td>Service Provider ID :</td>
                <td>${dbServiceProvider.id}</td> 
            </tr>
            <tr>
                <td>Name :</td>
                <td>${dbServiceProvider.name}</td> 
            </tr>
            <tr>
                <td>Request URL :</td>
                <td>${dbServiceProvider.requestUrl}</td> 
            </tr>
            <tr>
                <td>Response URL :</td>
                <td>${dbServiceProvider.responseUrl}</td> 
            </tr>
            <tr>
                <td>Account Number :</td>
                <td>${dbServiceProvider.accountNumber}</td> 
            </tr>
            <tr>
                <td>Bank IFSC Code :</td>
                <td>${dbServiceProvider.bankIFSCCode}</td> 
            </tr>
        </table>
        <a href="list">List all Service Providers</a>
        &nbsp;|&nbsp;
        <a href="create">Add New Service Provider</a>
        &nbsp;|&nbsp;
        <a href="edit?id=${dbBank.id}">Edit this Service Provider</a>
        &nbsp;|&nbsp;
        <a href="delete?id=${dbBank.id}">Delete this Service Provider</a>
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

<%@include file="../common/footer.jsp" %>