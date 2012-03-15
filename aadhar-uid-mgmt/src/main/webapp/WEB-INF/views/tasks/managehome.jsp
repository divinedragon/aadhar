<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Manage City, District and States";
%>
<%@include file="../common/include_header.jsp" %>

<link rel="stylesheet" href="../css/flexigrid.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/flexigrid.pack.css" type="text/css" media="screen" title="default" />

<div id="page-heading"><h1>Manage City, District and States</h1></div>


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
    
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
    <h2>All Cities</h2>
    <table id="cityTable"></table>
    <div id="districtTable"></div>
    <div id="stateTable"></div>

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

<script type="text/javascript" src="../js/flexigrid.js"></script>
<script type="text/javascript" src="../js/flexigrid.pack.js"></script>
<script type="text/javascript">
    <!--
    /* Flexigrid for City Table */
    $("#cityTable").flexigrid({
        url: '../city/list/json',
        method : "GET",
        dataType: 'json',
	    colModel : [
	        {display: 'City', name : 'city', width : 40, sortable : true, align: 'left'},
	        {display: 'State', name : 'state', width : 120, sortable : true, align: 'left'}
	        ],
	    buttons : [
	        {name: 'Add', bclass: 'add', onpress : function(){alert("Add");}},
	        {name: 'Delete', bclass: 'delete', onpress : function(){alert("Delete");}},
	        {separator: true}
	        ],
	    searchitems : [
	        {display: 'City', name : 'city'},
	        {display: 'State', name : 'state', isdefault: true}
	        ],
	    sortname: "city",
	    sortorder: "asc",
	    usepager: true,
	    title: 'Listing all Citites',
	    useRp: true,
	    rp: 15,
	    showTableToggleBtn: false,
	    width: 700,
	    height: 200
	});
    //-->
</script>

<%@include file="../common/footer.jsp" %>
