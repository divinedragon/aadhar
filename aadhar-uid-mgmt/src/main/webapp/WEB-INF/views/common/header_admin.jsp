<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%= PAGE_TITLE %></title>
<link rel="stylesheet" href="<c:url value="/resources/css/screen.css" />" type="text/css" media="screen" title="default" />
<!--[if IE]>
<link rel="stylesheet" media="all" type="text/css" href="<c:url value="/resources/css/pro_dropline_ie.css" />" />
<![endif]-->

<!--  Jquery core -->
<script src="<c:url value="/resources/js/jquery/jquery-1.5.1.min.js" />" type="text/javascript"></script>
 
<!--  checkbox styling script -->
<script src="<c:url value="/resources/js/jquery/ui.core.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery/ui.checkbox.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery/jquery.bind.js" />" type="text/javascript"></script>


<![if !IE 7]>

<!--  styled select box script version 1 -->
<script src="<c:url value="/resources/js/jquery/jquery.selectbox-0.5.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('.styledselect').selectbox({ inputClass: "selectbox_styled" });
});
</script>
 

<![endif]>


<!--  styled select box script version 2 --> 
<script src="<c:url value="/resources/js/jquery/jquery.selectbox-0.5_style_2.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('.styledselect_form_1').selectbox({ inputClass: "styledselect_form_1" });
    $('.styledselect_form_2').selectbox({ inputClass: "styledselect_form_2" });
});
</script>

<!--  styled select box script version 3 --> 
<script src="<c:url value="/resources/js/jquery/jquery.selectbox-0.5_style_2.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('.styledselect_pages').selectbox({ inputClass: "styledselect_pages" });
});
</script>

<!--  styled file upload script --> 
<script src="<c:url value="/resources/js/jquery/jquery.filestyle.js" />" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
    $("input.file_1").filestyle({ 
    image: "images/forms/upload_file.gif",
    imageheight : 29,
    imagewidth : 78,
    width : 300
    });
});
</script>
<script type="text/javascript">
$(function(){
    $('#mainform input[type=checkbox]').checkBox();
});
</script> 

<!-- Custom jquery scripts -->
<script src="<c:url value="/resources/js/jquery/custom_jquery.js" />" type="text/javascript"></script>
 
<!-- Tooltips -->
<script src="<c:url value="/resources/js/jquery/jquery.tooltip.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery/jquery.dimensions.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
    $('a.info-tooltip ').tooltip({
        track: true,
        delay: 0,
        fixPNG: true, 
        showURL: false,
        showBody: " - ",
        top: -35,
        left: 5
    });
});
</script> 

<!--  date picker script -->
<link rel="stylesheet" href="<c:url value="/resources/css/datePicker.css" />" type="text/css" />
<style type="text/css">
    label { color: #333333; }
</style>
<script src="<c:url value="/resources/js/jquery/date.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery/date.js" />" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
    $(function() {

        // initialise the "Select date" link
        $('#date-pick').datePicker(
        		// associate the link with a date picker
                {
                    createButton:false,
                    startDate:'01/01/2005',
                    endDate:'31/12/2020'
                }
            ).bind(
            	    // when the link is clicked display the date picker
                'click',
                function() {
                    updateSelects($(this).dpGetSelected()[0]);
                    $(this).dpDisplay();
                    return false;
                }
            ).bind(
            	    // when a date is selected update the SELECTs
                'dateSelected',
                function(e, selectedDate, $td, state) {
                    updateSelects(selectedDate);
                }
            ).bind(
            	'dpClosed',
            	function(e, selected) {
            	    updateSelects(selected[0]);
            	}
            );
    
        var updateSelects = function (selectedDate) {
            var selectedDate = new Date(selectedDate);
            $('#d option[value=' + selectedDate.getDate() + ']').attr('selected', 'selected');
            $('#m option[value=' + (selectedDate.getMonth()+1) + ']').attr('selected', 'selected');
            $('#y option[value=' + (selectedDate.getFullYear()) + ']').attr('selected', 'selected');
        }

        // listen for when the selects are changed and update the picker
        $('#d, #m, #y').bind(
        	'change',
        	function() {
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

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD> png fix -->
<script src="<c:url value="/resources/js/jquery/jquery.pngFix.pack.js" />" type="text/javascript"></script>
<script type="text/javascript">
    $(function() { $(document).pngFix( ); });
</script>
</head>
<body> 
<!-- Start: page-top-outer -->
<div id="page-top-outer">    

<!-- Start: page-top -->
<div id="page-top">

    <!-- start logo -->
    <div id="logo">
    <a href=""><img src="<c:url value="/resources/images/shared/logo.png" />" width="156" height="40" alt="" /></a>
    </div>
    <!-- end logo -->
    
    <!--  start top-search -->
    <div id="top-search">
        <table border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td><input type="text" value="Search" onblur="if (this.value=='') { this.value='Search'; }" onfocus="if (this.value=='Search') { this.value=''; }" class="top-search-inp" /></td>
        <td>
         
        <select  class="styledselect">
            <option value="">All</option>
            <option value="">Products</option>
            <option value="">Categories</option>
            <option value="">Clients</option>
            <option value="">News</option>
        </select> 
         
        </td>
        <td>
        <input type="image" src="<c:url value="/resources/images/shared/top_search_btn.gif" />"  />
        </td>
        </tr>
        </table>
    </div>
     <!--  end top-search -->
     <div class="clear"></div>

</div>
<!-- End: page-top -->

</div>
<!-- End: page-top-outer -->
    
<div class="clear">&nbsp;</div>