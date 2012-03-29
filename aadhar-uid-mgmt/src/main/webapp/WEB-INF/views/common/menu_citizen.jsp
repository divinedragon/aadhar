<!--  start nav-outer-repeat................................................................................................. START -->
<div class="nav-outer-repeat"> 
<!--  start nav-outer -->
<div class="nav-outer"> 

        <!-- start nav-right -->
        <div id="nav-right">
        
            <div class="nav-divider">&nbsp;</div>
            <div class="showhide-account"><img src="../images/shared/nav/nav_myaccount.gif" width="93" height="14" alt="" /></div>
            <div class="nav-divider">&nbsp;</div>
            <a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />" id="logout"><img src="../images/shared/nav/nav_logout.gif" width="64" height="14" alt="Logout" /></a>
            <div class="clear">&nbsp;</div>
        
        
        </div>
        <!-- end nav-right -->


        <!--  start nav -->
        <div class="nav">
        <div class="table">
        
        <ul class="<%= ("Home".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#nogo"><b>Aadhar IDs</b><!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
        <div class="select_sub<%= ("Aadhar IDs".equals(MENU_NAME)) ? " show" : "" %>">
            <ul class="sub">
                <li<%= ("View UIDs".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../citizen/list">View UIDs</a></li>
                <li<%= ("Add New UID".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../citizen/create">Add New UID</a></a></li>
                <li<%= ("Modify UID".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../citizen/list">Modify UID</a></li>
                <li<%= ("Disable UID".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../citizen/list">Disable UID</a></li>
            </ul>
        </div>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        </ul>
        
        <div class="nav-divider">&nbsp;</div>
        
        <div class="clear"></div>
        </div>
        <div class="clear"></div>
        </div>
        <!--  start nav -->

</div>
<div class="clear"></div>
<!--  start nav-outer -->
</div>
<!--  start nav-outer-repeat................................................... END -->
 
<div class="clear"></div>

<!-- start content-outer -->
<div id="content-outer">
<!-- start content -->
<div id="content">

