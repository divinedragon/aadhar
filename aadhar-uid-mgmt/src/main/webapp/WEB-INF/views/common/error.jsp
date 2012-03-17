<%@page contentType="text/html; charset=UTF-8" %>
<%
    String PAGE_TITLE = ":: Aadhar :: Error";
%>
<%@include file="../common/include_header.jsp" %>

<div id="page-heading"><h1>Oops. We have encountered an unexpected behaviour.</h1></div>

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
        <!--  start table-content  -->
        <div id="table-content">

        <!--  start message-red -->
        <div id="message-red">
        <table border="0" width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td class="red-left">Error: ${msg}. &nbsp;<a href="/">Go to Home</a></td>
            <td class="red-right"><a class="close-red"><img src="../images/table/icon_close_red.gif"   alt="" /></a></td>
        </tr>
        </table>
        </div>
        <!--  end message-red -->

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

<%@include file="../common/footer.jsp" %>