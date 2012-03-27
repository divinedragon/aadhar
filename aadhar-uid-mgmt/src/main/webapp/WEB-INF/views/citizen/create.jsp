<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String PAGE_TITLE = ":: Aadhar :: Add New Citizen";
    String MENU_NAME  = "Aadhar IDs";
    String LINK_NAME  = "Add New UID";
%>
<%@include file="../common/include_header.jsp" %>

<link rel="stylesheet" href="../css/jquery.validate.css" type="text/css" media="screen" title="default" />
<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" title="default" />
<style type="text/css">
    table td { padding: 5px;}
    label { color:black; font-weight:bold; font-size:14px;  }
    span.ValidationErrors { display: inline-block; }
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

    var strDate = selectedDate.getDate() + "-" + (selectedDate.getMonth()+1) + "-" + selectedDate.getFullYear();
    $("#txtDateOfBirth").val(strDate);
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

            var strDate = $('#d').val() + "-" + $('#m').val() + "-" + $('#y').val();
            $("#txtDateOfBirth").val(strDate);
        }
    );
// default the position of the selects to today
var today = new Date();

$("#y").html("");
/* Create the Years from 1900 to current year */
for (var i=1900; i <= today.getFullYear(); i++) {
    $("#y").append('<option value="' + i + '" ' + ((i == today.getFullYear()) ? 'selected="selected"':'') +'>' + i + '</option>');
}
updateSelects(today.getTime());

// and update the datePicker to reflect it...
$('#d').trigger('change');

});

$(function(){
    $('#cmbLocalState, #cmbPermanentState').bind('change', function() {

        stateId = $(this).val();

        cmbType = $(this).attr("id").replace("cmb", "").replace("State", "");

        cityCmbName = "#cmb" + cmbType + "City";
        districtCmbName = "#cmb" + cmbType + "District";

        $.ajax({
            type: "GET",
            url: "../city/listForState/" + stateId,
            dataType: "json",
            success: function (data) {
                        data = eval(data);
                        $(cityCmbName).html("");
                        $.each(data.object, function(i, city){
                            $(cityCmbName).append('<option value="' + city.id + '">' + city.name + '</option>');
                        });
            },
        });

        $.ajax({
            type: "GET",
            url: "../district/listForState/" + stateId,
            dataType: "json",
            success: function (data) {
                        data = eval(data);
                        $(districtCmbName).html("");
                        $.each(data.object, function(i, district){
                            $(districtCmbName).append('<option value="' + district.id + '">' + district.name + '</option>');
                        });
            },
        });
    });
    $('#cmbLocalDistrict').html("<option value='0'>Select District</option>");
    $('#cmbPermanentDistrict').html("<option value='0'>Select District</option>");
    $('#cmbLocalCity').html("<option value='0'>Select City</option>");
    $('#cmbPermanentCity').html("<option value='0'>Select City</option>");
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
            <table style="width:100%;">
            <tr>
                <td style="width:25%;"><label for="txtName">Name :</label></td>
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
                    <span id="gender">
                    <c:forEach items="${genders}" var="gender" varStatus="loop">
                        <form:radiobutton path="gender" id="gender_${gender.value}" value="${gender.key}" />
                        <label for="gender_${gender.value}">${gender.value}</label>
                        &nbsp;&nbsp;
                    </c:forEach>
                    </span>
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
                    <form:hidden path="dateOfBirth" id="txtDateOfBirth" />
                </td> 
            </tr>
            <tr>
                <td><label for="txtEmail">Email :</label></td>
                <td><form:input path="email" id="txtEmail" /></td> 
            </tr>
            <tr>
                <td><label for="txtMobile">Mobile :</label></td>
                <td><form:input path="mobile" id="txtMobile" /></td> 
            </tr>
            <tr>
                <td><label for="cmbBank">Bank :</label></td>
                <td><form:select path="account.bank" id="cmbState" items="${banks}" itemLabel="name" itemValue="id" /></td> 
            </tr>
            <tr>
                <td><label>Access Role :</label></td>
                <td>
                    <span id="accessRoles">
                    <c:forEach items="${accessRoles}" var="accessRole" varStatus="loop">
                        <form:radiobutton path="accessRole" id="accessRoles_${accessRole.value}" value="${accessRole.key}" />
                        <label for="accessRoles_${accessRole.value}">${accessRole.value}</label>
                        &nbsp;&nbsp;
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table style="width:100%;">
                        <tr>
                            <td>
                                <table style="border:1px solid black;width:100%;">
                                    <caption style="font-size:16px; font-weigh:bold;padding:5px">Local Address:</caption>
                                    <tr>
                                        <td><label for="txtLocalCO">C/O</label></td>
                                        <td><form:input path="localAddress.careOf" id="txtLocalCO" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtLocalLine1">Line 1</label></td>
                                        <td><form:input path="localAddress.addressLine1" id="txtLocalLine1" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtLocalLine2">Line 2</label></td>
                                        <td><form:input path="localAddress.addressLine2" id="txtLocalLine2" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtLocalLine3">Line 3</label></td>
                                        <td><form:input path="localAddress.addressLine3" id="txtLocalLine3" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtLocalArea">Area</label></td>
                                        <td><form:input path="localAddress.area" id="txtLocalArea" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbLocalState">State</label></td>
                                        <td><form:select path="localAddress.state" id="cmbLocalState" items="${states}" itemLabel="state" itemValue="id" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbLocalDistrict">District</label></td>
                                        <td><form:select path="localAddress.district" id="cmbLocalDistrict" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbLocalCity">City</label></td>
                                        <td><form:select path="localAddress.city" id="cmbLocalCity" /></td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <table style="border:1px solid black;width:100%;">
                                    <caption style="font-size:16px; font-weigh:bold;padding:5px" id="addressCheck">
                                        Permanent Address:<input type="checkbox" />
                                    </caption>
                                    <tr>
                                        <td><label for="txtPermanentCO">C/O</label></td>
                                        <td><form:input path="permanentAddress.careOf" id="txtPermanentCO" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtPermanentLine1">Line 1</label></td>
                                        <td><form:input path="permanentAddress.addressLine1" id="txtPermanentLine1" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtPermanentLine2">Line 2</label></td>
                                        <td><form:input path="permanentAddress.addressLine2" id="txtPermanentLine2" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtPermanentLine3">Line 3</label></td>
                                        <td><form:input path="permanentAddress.addressLine3" id="txtPermanentLine3" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="txtPermanentArea">Area</label></td>
                                        <td><form:input path="permanentAddress.area" id="txtPermanentArea" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbPermanentState">State</label></td>
                                        <td><form:select path="permanentAddress.state" id="cmbPermanentState" items="${states}" itemLabel="state" itemValue="id" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbPermanentDistrict">District</label></td>
                                        <td><form:select path="permanentAddress.district" id="cmbPermanentDistrict" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="cmbPermanentCity">City</label></td>
                                    <td><form:select path="permanentAddress.city" id="cmbPermanentCity" /></td>
                                    </tr>
                                </table>
                            </td>
                            
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2">
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
        jQuery("#txtEmail").validate({
            expression: "if (VAL.match(/^[^\\W][a-zA-Z0-9\\_\\-\\.]+([a-zA-Z0-9\\_\\-\\.]+)*\\@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\.[a-zA-Z]{2,4}$/)) return true; else return false;",
            message: "Please enter a valid Email ID"
        });
        jQuery("#gender").validate({
            expression: "if (isChecked(SelfID)) return true; else return false;",
            message: "Please select your gender"
        });
        jQuery("#accessRoles").validate({
            expression: "if (isChecked(SelfID)) return true; else return false;",
            message: "Please select the Access Role"
        });
        jQuery("#txtMobile").validate({
            expression: "if (VAL.match(/^[0-9]{10}$/)) return true; else return false;",
            message: "Please provide your 10-digit mobile number"
        });
        jQuery("#txtLocalCO").validate({
            expression: "if (VAL.length > 0){ if (VAL.length > 5) return true; else false; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtLocalLine1").validate({
            expression: "if (VAL.length > 5) return true; else false;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtLocalLine2").validate({
            expression: "if (VAL.length > 0){ if (VAL.length > 5) return true; else false; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtLocalLine3").validate({
            expression: "if (VAL.length > 0){ if (VAL.length > 5) return true; else false; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtLocalArea").validate({
            expression: "if (VAL.length > 5) return true; else false;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#cmbLocalState").validate({
            expression: "if ((VAL != '0') && (VAL != '')) return true; else return false;",
            message: "Please select a state"
        });
        jQuery("#cmbLocalDistrict").validate({
            expression: "if ((VAL != '0') && (VAL != '')) return true; else return false;",
            message: "Please select a district"
        });
        jQuery("#cmbLocalCity").validate({
            expression: "if ((VAL != '0') && (VAL != '')) return true; else return false;",
            message: "Please select a city"
        });
        jQuery("#txtPermanentCO").validate({
            expression: "if (!isChecked('addressCheck')) { if (VAL.length > 0) { if (VAL.length > 5) return true; else false; } else return true; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtPermanentLine1").validate({
            expression: "if (!isChecked('addressCheck')) { if (VAL.length > 5) return true; else false; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtPermanentLine2").validate({
            expression: "if (!isChecked('addressCheck')) { if (VAL.length > 0){ if (VAL.length > 5) return true; else false; } else return true; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtPermanentLine3").validate({
            expression: "if (!isChecked('addressCheck')) { if (VAL.length > 0){ if (VAL.length > 5) return true; else false; } else return true; } else return true; ",
            message: "Minimum 5 characters are required"
        });
        jQuery("#txtPermanentArea").validate({
            expression: "if (!isChecked('addressCheck')) { if (VAL.length > 5) return true; else false; } else return true;",
            message: "Minimum 5 characters are required"
        });
        jQuery("#cmbPermanentState").validate({
            expression: "if (!isChecked('addressCheck')) { if ((VAL != '0') && (VAL != '')) return true; else return false; } else return true;",
            message: "Please select a state"
        });
        jQuery("#cmbPermanentDistrict").validate({
            expression: "if (!isChecked('addressCheck')) { if ((VAL != '0') && (VAL != '')) return true; else return false;} else return true;",
            message: "Please select a district"
        });
        jQuery("#cmbPermanentCity").validate({
            expression: "if (!isChecked('addressCheck')) { if ((VAL != '0') && (VAL != '')) return true; else return false;} else return true;",
            message: "Please select a city"
        });
    });
    /* ]]> */
</script>

<%@include file="../common/footer.jsp" %>
