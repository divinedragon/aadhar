<%@page language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>:: Aadhar :: Login</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" title="default" />
<!--  jquery core -->
<script src="../js/jquery/jquery-1.5.1.min.js" type="text/javascript"></script>

<!-- Custom jquery scripts -->
<script src="../js/jquery/custom_jquery.js" type="text/javascript"></script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="../js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
$(document).pngFix();
});
</script>
</head>
<body id="login-bg"> 
 
<!-- Start: login-holder -->
<div id="login-holder">

    <!-- start logo -->
    <div id="logo-login">
        <a href="/"><img src="../images/shared/logo.png" width="156" height="40" alt="" /></a>
    </div>
    <!-- end logo -->
    
    <div class="clear"></div>

    <%
        if (request.getParameter("login_error") != null) {
            Exception ex = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            String errorMsg = (ex != null) ? ex.getMessage() : "none";
            String lastUser = (String) session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
            if (lastUser == null) {
                lastUser = "";
            }
    %>
    
    <div class="border:1px solid red;background:#CE2700;color:white;">
        Unable to login. Reason <%= errorMsg %>
    </div>
    <%
        }
    %>
    
    <!--  start loginbox ................................................................................. -->
    <div id="loginbox">
    
    <!--  start login-inner -->
    <form name="f" action='<spring:url value="/j_spring_security_check" htmlEscape="true" />' method="post">
    <div id="login-inner">
        <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <th>Username</th>
            <td><input type="text" class="login-inp" name="j_username" id="username"/></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><input type="password" value="***" onfocus="this.value=''" class="login-inp" name="j_password" /></td>
        </tr>
        <tr>
            <th></th>
            <td valign="top">&nbsp;</td>
        </tr>
        <tr>
            <th></th>
            <td><input type="button" class="submit-login" onclick="document.f.submit();"/></td>
        </tr>
        </table>
    </div>
    </form>
    <!--  end login-inner -->
    <div class="clear"></div>
    <a href="" class="forgot-pwd">Forgot Password?</a>
 </div>
 <!--  end loginbox -->
 
    <!--  start forgotbox ................................................................................... -->
    <div id="forgotbox">
        <div id="forgotbox-text">Please send us your email and we'll reset your password.</div>
        <!--  start forgot-inner -->
        <div id="forgot-inner">
        <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <th>Email address:</th>
            <td><input type="text" value="" class="login-inp" /></td>
        </tr>
        <tr>
            <th> </th>
            <td><input type="button" class="submit-login"  /></td>
        </tr>
        </table>
        </div>
        <!--  end forgot-inner -->
        <div class="clear"></div>
        <a href="" class="back-login">Back to login</a>
    </div>
    <!--  end forgotbox -->

</div>
<!-- End: login-holder -->
</body>
</html>
