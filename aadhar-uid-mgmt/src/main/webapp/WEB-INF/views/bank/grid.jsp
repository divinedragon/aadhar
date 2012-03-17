<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: View all Banks";
%>
<%@include file="../common/include_header.jsp" %>

<link rel="stylesheet" href="../css/flexigrid.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/flexigrid.pack.css" type="text/css" media="screen" title="default" />
<!-- IE6 "fix" for the close png image -->
<!--[if lt IE 7]>
<link type='text/css' href='../css/basic_ie.css' rel='stylesheet' media='screen' />
<![endif]-->

<div id="page-heading"><h1>View all Banks</h1></div>

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
        <div style="float:left;margin-right:10px;">
            <div id="bankTable"></div>
        </div>
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
    $("#bankTable").flexigrid({
        url: '../bank/list/json',
        method : "GET",
        dataType: 'json',
        colModel : [
            {
                display: 'Id',
                name : 'id',
                width : 50,
                sortable : true,
                align: 'left',
                hide : true
            },
            {
                display: 'Name',
                name : 'name',
                width : 150,
                sortable : true,
                align: 'left'
            },
            {
                display: 'Url',
                name : 'url',
                width : 650,
                sortable : true,
                align: 'left'
            }
        ],
        buttons : [
            {
                name: 'Add',
                bclass: 'add',
                onpress : gridButtonAction
            },
            {
                name: 'Edit',
                bclass: 'edit',
                onpress : gridButtonAction
            },
            {
                name: 'View',
                bclass: 'view',
                onpress : gridButtonAction
            },
            {
                name: 'Delete',
                bclass: 'delete',
                onpress : gridButtonAction
            },
            {separator: true}
        ],
        searchitems : [
            { display: 'Name', name : 'name', isdefault: true },
            { display: 'Url', name : 'url' }
        ],
        sortname: "name",
        sortorder: "asc",
        usepager: true,
        singleSelect : true,
        title: 'Listing all Banks',
        useRp: true,
        rp: 50,
        showTableToggleBtn: true,
        width: 900,
        height: 300,
        preProcess : function (json) {
            
            var results = new Array();
            jQuery.each(json.object, function() {
                
                var row = {};
                var cell = new Array();
                
                jQuery.each(this, function(k, v) {
                    if (k == "url" && v == "") {
                        cell.push("NA");
                    } else {
                        cell.push(this);
                    }
                });

                row["cell"] = cell;
                results.push(row);
            });
            
            return {
                rows : results,
                page : json.pageCount,
                total : json.total
            };
        }
    });

    /* Function which processes the button actions for Grid */
    function gridButtonAction(command, grid) {

        /* Check which button did the user press */
        if (command == "Add") {
            /* Add button pressed. Re-direct the user to Add screen */
            alert("Add functionality not yet implemented");

        } else {
            /* For update, delete and view, the user needs to select a row
             * before performing any operation.
             */
            var selectedRowCount = $('.trSelected', grid).length;
            
            if (selectedRowCount == 0) {
                alert("Please select a row to perform this operation.");
                return ;
            }

            /* There is a selected row. Check what kind of operation did the
             * user requested.
             */
             if (command == "Delete") {
                 /* User requested delete operation. */
                 alert("Delete functionality not yet implemented");
             } else if (command == "Edit") {
                 /* User requested edit operation */
                 alert("Update functionality not yet implemented");
             } else if (command == "View") {
                 /* User requested view operation */
                 alert("View functionality not yet implemented");
             }
        }
    }

    //-->
</script>

<%@include file="../common/footer.jsp" %>