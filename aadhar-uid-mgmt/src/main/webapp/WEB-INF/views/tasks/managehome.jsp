<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Manage States, Districts and Cities";
    String MENU_NAME  = "Tasks";
    String LINK_NAME  = "Manage City, District &amp; States";
%>
<%@include file="../common/include_header.jsp" %>

<link rel="stylesheet" href="../css/flexigrid.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/flexigrid.pack.css" type="text/css" media="screen" title="default" />
<link type='text/css' href='../css/basic.css' rel='stylesheet' media='screen' />
<!-- IE6 "fix" for the close png image -->
<!--[if lt IE 7]>
<link type='text/css' href='../css/basic_ie.css' rel='stylesheet' media='screen' />
<![endif]-->

<style type="text/css">
    .flexigrid div.sDiv2 .qsbox { width:100px }
    #indicator {position:absolute; bottom:40%;left:40%;}
</style>
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
        <div style="float:left;margin-right:10px;">
            <h2>All States</h2>
            <div id="stateTable"></div>
        </div>

        <div style="float:left;margin-right:10px;">
            <h2>All Districts</h2>
            <div id="districtTable"></div>
        </div>

        <div style="float:left">
            <h2>All Cities</h2>
            <div id="cityTable"></div>
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

<!-- modal content -->
<div id="basic-modal-content" style="position:relative;">
    <iframe src="../city/create" style="border:0px;height:350px;width:100%;overflow:hidden;"></iframe>
    <div id="indicator"><img src="../images/ajax-loader.gif"/></div>
</div>

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
            {
                display: 'Id',
                name : 'id',
                width : 20,
                sortable : true,
                align: 'left',
                hide : true
            },
            {
                display: 'City',
                name : 'city',
                width : 160,
                sortable : true,
                align: 'left'
            },
            {
                display: 'State',
                name : 'state',
                width : 160,
                sortable : true,
                align: 'left'
            }
        ],
        buttons : [
            {
                name: 'Add',
                bclass: 'add',
                onpress : cityGridButtonAction
            },
            {
                name: 'Edit',
                bclass: 'edit',
                onpress : cityGridButtonAction
            },
            {
                name: 'View',
                bclass: 'view',
                onpress : cityGridButtonAction
            },
            {
                name: 'Delete',
                bclass: 'delete',
                onpress : cityGridButtonAction
            },
            {separator: true}
        ],
        searchitems : [
            { display: 'City', name : 'city', isdefault: true },
            { display: 'State', name : 'state' }
        ],
        sortname: "city",
        sortorder: "asc",
        usepager: true,
        singleSelect : true,
        title: 'Listing all Citites',
        useRp: true,
        rp: 50,
        showTableToggleBtn: true,
        width: 350,
        height: 250,
        preProcess : function (json) {
            
            var results = new Array();
            jQuery.each(json.object, function() {
                
                var row = {};
                var cell = new Array();
                
                jQuery.each(this, function(k, v) {
                    if (k == "state" && v == "") {
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

    /* Function which processes the button actions for Grid City */
    function cityGridButtonAction(command, grid) {

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

    /* Flexigrid for District Table */
    $("#districtTable").flexigrid({
        url: '../district/list/json',
        method : "GET",
        dataType: 'json',
        colModel : [
            {
                display: 'Id',
                name : 'id',
                width : 20,
                sortable : true,
                align: 'left',
                hide : true
            },
            {
                display: 'District',
                name : 'district',
                width : 160,
                sortable : true,
                align: 'left'
            },
            {
                display: 'State',
                name : 'state',
                width : 160,
                sortable : true,
                align: 'left'
            }
        ],
        buttons : [
            {
                name: 'Add',
                bclass: 'add',
                onpress : cityGridButtonAction
            },
            {
                name: 'Edit',
                bclass: 'edit',
                onpress : cityGridButtonAction
            },
            {
                name: 'View',
                bclass: 'view',
                onpress : cityGridButtonAction
            },
            {
                name: 'Delete',
                bclass: 'delete',
                onpress : cityGridButtonAction
            },
            {separator: true}
        ],
        searchitems : [
            { display: 'District', name : 'district', isdefault: true },
            { display: 'State', name : 'state' }
        ],
        sortname: "district",
        sortorder: "asc",
        usepager: true,
        singleSelect : true,
        title: 'Listing all Districts',
        useRp: true,
        rp: 50,
        showTableToggleBtn: true,
        width: 350,
        height: 250,
        preProcess : function (json) {
            
            var results = new Array();
            jQuery.each(json.object, function() {
                
                var row = {};
                var cell = new Array();
                
                jQuery.each(this, function(k, v) {
                    if (k == "state" && v == "") {
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

    /* Flexigrid for State Table */
    $("#stateTable").flexigrid({
        url: '../state/list/json',
        method : "GET",
        dataType: 'json',
        colModel : [
            {
                display: 'Id',
                name : 'id',
                width : 20,
                sortable : true,
                align: 'left',
                hide : true
            },
            {
                display: 'State',
                name : 'state',
                width : 170,
                sortable : true,
                align: 'left'
            }
        ],
        buttons : [
            {
                name: 'Add',
                bclass: 'add',
                onpress : cityGridButtonAction
            },
            {
                name: 'Edit',
                bclass: 'edit',
                onpress : cityGridButtonAction
            },
            {
                name: 'View',
                bclass: 'view',
                onpress : cityGridButtonAction
            },
            {
                name: 'Delete',
                bclass: 'delete',
                onpress : cityGridButtonAction
            },
            {separator: true}
        ],
        searchitems : [ { display: 'State', name : 'state' } ],
        sortname: "state",
        sortorder: "asc",
        usepager: true,
        singleSelect : true,
        title: 'Listing all States',
        useRp: true,
        rp: 50,
        showTableToggleBtn: true,
        width: 200,
        height: 200,
        preProcess : function (json) {
            
            var results = new Array();
            jQuery.each(json.object, function() {
                
                var row = {};
                var cell = new Array();
                
                jQuery.each(this, function(k, v) {
                    if (k == "state" && v == "") {
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

    //-->
</script>
<script type="text/javascript" src="../js/jquery.simplemodal.js"></script>
<script type="text/javascript">
    $(function() {
        $('#basic-modal-content').modal();
    });
</script>

<%@include file="../common/footer.jsp" %>