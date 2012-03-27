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
        
            <!--  start account-content -->    
            <div class="account-content">
            <div class="account-drop-inner">
                <a href="" id="acc-settings">Settings</a>
                <div class="clear">&nbsp;</div>
                <div class="acc-line">&nbsp;</div>
                <a href="" id="acc-details">Personal details </a>
                <div class="clear">&nbsp;</div>
                <div class="acc-line">&nbsp;</div>
                <a href="" id="acc-project">Project details</a>
                <div class="clear">&nbsp;</div>
                <div class="acc-line">&nbsp;</div>
                <a href="" id="acc-inbox">Inbox</a>
                <div class="clear">&nbsp;</div>
                <div class="acc-line">&nbsp;</div>
                <a href="" id="acc-stats">Statistics</a> 
            </div>
            </div>
            <!--  end account-content -->
        
        </div>
        <!-- end nav-right -->


        <!--  start nav -->
        <div class="nav">
        <div class="table">
        
        <ul class="<%= ("Aadhar IDs".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#nogo"><b>Aadhar IDs</b><!--[if IE 7]><!--></a><!--<![endif]-->
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
                            
        <ul class="<%= ("Service Providers".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#nogo"><b>Service Providers</b><!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
        <div class="select_sub<%= ("Service Providers".equals(MENU_NAME)) ? " show" : "" %>">
            <ul class="sub">
                <li<%= ("View All".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../serviceprovider/list">View All</a></li>
                <li<%= ("Add New".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../serviceprovider/create">Add New</a></li>
                <li<%= ("Modify".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../serviceprovider/list">Modify</a></li>
                <li<%= ("Delete".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../serviceprovider/list">Delete</a></li>
            </ul>
        </div>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        </ul>
        
        <div class="nav-divider">&nbsp;</div>
        
        <ul class="<%= ("Tasks".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#"><b>Tasks</b><!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
        <div class="select_sub<%= ("Tasks".equals(MENU_NAME)) ? " show" : "" %>">
            <ul class="sub">
                <li<%= ("Generate ID Cards".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="#nogo">Generate ID Cards</a></li>
                <li<%= ("Generate Birth Certificates".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="#nogo">Generate Birth Certificates</a></li>
                <li<%= ("Manage City, District &amp; States".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../tasks/managehome">Manage City, District &amp; States</a></li>
            </ul>
        </div>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        </ul>
        
        <div class="nav-divider">&nbsp;</div>
        
        <ul class="<%= ("Reports".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#nogo"><b>Reports</b><!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
        <div class="select_sub<%= ("Reports".equals(MENU_NAME)) ? " show" : "" %>">
            <ul class="sub">
                <li<%= ("Registration".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="#nogo">Registration</a></li>
                <li<%= ("Transaction".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="#nogo">Transaction</a></li>
            </ul>
        </div>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        </ul>
        
        <div class="nav-divider">&nbsp;</div>
        
        <ul class="<%= ("Banks".equals(MENU_NAME)) ? "current" : "select" %>"><li><a href="#nogo"><b>Banks</b><!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
        <div class="select_sub<%= ("Banks".equals(MENU_NAME)) ? " show" : "" %>">
            <ul class="sub">
                <li<%= ("View all Banks".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../bank/list">View all Banks</a></li>
                <li<%= ("Register New Bank".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../bank/create">Register New Bank</a></li>
                <li<%= ("Modify Bank Details".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../bank/list">Modify Bank Details</a></li>
                <li<%= ("Disable Bank".equals(LINK_NAME)) ? " class='sub_show'" : "" %>><a href="../bank/list">Disable Bank</a></li>
            </ul>
        </div>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        </ul>
        
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

