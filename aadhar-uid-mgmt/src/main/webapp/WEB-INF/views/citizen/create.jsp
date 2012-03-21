<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Add New Citizen";
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


<!--  date picker script -->
<link rel="stylesheet" href="../css/datePicker.css" type="text/css" />
<script src="../js/jquery/date.js" type="text/javascript"></script>
<script src="../js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
        $(function() {

// initialise the "Select date" link
$('#date-pick')
    .datePicker(
        // associate the link with a date picker
        {
            createButton:false,
            startDate:'01/01/1900',
            endDate:'31/12/2050'
        }
    ).bind(
        // when the link is clicked display the date picker
        'click',
        function()
        {
            updateSelects($(this).dpGetSelected()[0]);
            $(this).dpDisplay();
            return false;
        }
    ).bind(
        // when a date is selected update the SELECTs
        'dateSelected',
        function(e, selectedDate, $td, state)
        {
            updateSelects(selectedDate);
        }
    ).bind(
        'dpClosed',
        function(e, selected)
        {
            updateSelects(selected[0]);
        }
    );
    
var updateSelects = function (selectedDate)
{
    var selectedDate = new Date(selectedDate);
    $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
    $('#m option[value=' + (selectedDate.getMonth()+1) + ']').attr('selected', 'selected');
    $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
}
// listen for when the selects are changed and update the picker
$('#d, #m, #y')
    .bind(
        'change',
        function()
        {
            var d = new Date(
                        $('#y').val(),
                        $('#m').val()-1,
                        $('#d').val()
                    );
            $('#date-pick').dpSetSelected(d.asString());
        }
    );

// default the position of the selects to today
var today = new Date();
updateSelects(today.getTime());

// and update the datePicker to reflect it...
$('#d').trigger('change');

});
</script>


<div id="page-heading"><h1>Add New Citizen</h1></div>

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
    <h2>Enter new Citizen details</h2>
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
    <td>
        <form:form id="citizenForm" method="post" action="create" modelAttribute="newCitizen">
            <table>
            <tr>
                <td><label for="txtName">Name :</label></td>
                <td><form:input path="name" id="txtName" maxlength="200" /></td> 
            </tr>
            <tr>
                <td><label for="txtPassword">Password :</label></td>
                <td><form:password path="password" id="txtPassword" /></td> 
            </tr>
            <tr>
                <td><label for="txtConfirmPassword">Confirm Password :</label></td>
                <td><input type="password" id="txtConfirmPassword" /></td> 
            </tr>
            <tr>
                <td><label>Gender :</label></td>
                <td>
                    <c:forEach items="${genders}" var="gender" varStatus="loop">
                        <form:radiobutton path="gender" id="${gender.value}" value="${gender.key}" />
                        <label for="${gender.value}">${gender.value}</label>
                        &nbsp;&nbsp;
                    </c:forEach>
                </td> 
            </tr>
            <tr>
                <td><label>Date of Birth :</label></td>
                <td>
                    <select id="d" class="styledselect-day">
                        <option value="">dd</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>

                    <select id="m" class="styledselect-month">
                        <option value="">mmm</option>
                        <option value="1">Jan</option>
                        <option value="2">Feb</option>
                        <option value="3">Mar</option>
                        <option value="4">Apr</option>
                        <option value="5">May</option>
                        <option value="6">Jun</option>
                        <option value="7">Jul</option>
                        <option value="8">Aug</option>
                        <option value="9">Sep</option>
                        <option value="10">Oct</option>
                        <option value="11">Nov</option>
                        <option value="12">Dec</option>
                    </select>
                    <select  id="y"  class="styledselect-year">
                        <option value="">yyyy</option>
                        <option value="2005">2005</option>
                        <option value="2006">2006</option>
                        <option value="2007">2007</option>
                        <option value="2008">2008</option>
                        <option value="2009">2009</option>
                        <option value="2010">2010</option>
                    </select>
                    <a href="#" id="date-pick"><img src="../images/forms/icon_calendar.jpg" alt="" style="vertical-align:bottom;margin-top:-5px" /></a>
                </td> 
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" class="form-submit" />
                    <input type="reset" value="Reset" class="form-reset"/>
                </td>
            </tr>
            </table>
        </form:form>
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

<script src="../js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="../js/jquery/jquery.validation.functions.js" type="text/javascript"></script>
<script type="text/javascript">
    /* <![CDATA[ */
    jQuery(function(){
        jQuery("#txtName").validate({
            expression: "if (VAL.match(/^[A-Za-z\s]{3,100}$/)) return true; else return false;",
            message: "Only Alphabets allowed with minimum 3 characters"
        });
        jQuery("#txtPassword").validate({
            expression: "if (VAL.length > 5 && VAL) return true; else return false;",
            message: "Please enter a valid Password with minimum 6 characters"
        });
        jQuery("#txtConfirmPassword").validate({
            expression: "if ((VAL == jQuery('#txtPassword').val()) && VAL) return true; else return false;",
            message: "Confirm password field doesn't match the password field"
        });
        jQuery("#txtRequestUrl").validate({
            expression: "if (VAL.length > 5 && VAL) return true; else return false;",
            message: "Please provide a valid url"
        });
        jQuery("#txtResponseUrl").validate({
            expression: "if (VAL.length > 5 && VAL) return true; else return false;",
            message: "Please provide a valid url"
        });
        jQuery("#txtAccountNumber").validate({
            expression: "if (VAL.match(/^[A-Za-z0-9]{4,20}$/)) return true; else return false;",
            message: "Please provide a valid account number"
        });
        jQuery("#txtBankIFSCCode").validate({
            expression: "if (VAL.match(/^[A-Za-z0-9]{4,20}$/)) return true; else return false;",
            message: "Please provide a valid bank IFSC Code"
        });
    });
    /* ]]> */
</script>

<%@include file="../common/footer.jsp" %>
