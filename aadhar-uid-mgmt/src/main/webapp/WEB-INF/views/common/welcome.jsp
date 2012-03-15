<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    /* Check which type of user is this - Admin or general */
    /* We will include the appropriate header file */
    
%>
<%
    String PAGE_TITLE = ":: Aadhar :: Home Page";
%>
<%@include file="../common/include_header.jsp" %>


<div id="page-heading"><h1>Add new UID</h1></div>


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
    
    
        <!--  start step-holder -->
        <div id="step-holder">
            <div class="step-no">1</div>
            <div class="step-dark-left"><a href="">Personal Details</a></div>
            <div class="step-dark-right">&nbsp;</div>
            <div class="step-no-off">2</div>
            <div class="step-light-left">Address</div>
            <div class="step-light-right">&nbsp;</div>
            <div class="step-no-off">3</div>
            <div class="step-light-left">Contact</div>
            <div class="step-light-round">&nbsp;</div>
            <div class="clear"></div>
        </div>
        <!--  end step-holder -->
    
        <!-- start id-form -->
        <form id="mainform" action="">
        <table border="0" cellpadding="0" cellspacing="0" id="id-form">
        <tr>
            <th valign="top"><label for="txtUid">UID:</label></th>
            <td><input id="txtUid" name="txtUid" type="text" class="inp-form" disabled="disabled" /></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <th valign="top"><label for="txtPassword">Password:</label></th>
            <td><input id="txtPassword" name="txtPassword" type="password" class="inp-form-error" /></td>
            <td>
                <div class="error-left"></div>
                <div class="error-inner">This field is required.</div>
            </td>
        </tr>
        <tr>
            <th valign="top"><label for="txtConfirmPassword">Re-enter Password:</label></th>
            <td><input id="txtConfirmPassword" name="txtConfirmPassword" type="password" class="inp-form-error" /></td>
            <td>
                <div class="error-left"></div>
                <div class="error-inner">This field is required.</div>
            </td>
        </tr>
        <tr>
            <th valign="top"><label for="txtName">Full Name:</label></th>
            <td><input id="txtName" name="txtName" type="text" class="inp-form" /></td>
            <td>
            <div class="error-left"></div>
            <div class="error-inner">This field is required.</div>
            </td>
        </tr>
        <tr>
            <th valign="top"><label>Gender:</label></th>
            <td>
                <input id="txtMale" name="txtGender" type="radio" value="M" class="inp-form" />&nbsp;<label for="txtMale">Male</label><br/>
                <input id="txtFemale" name="txtGender" type="radio" value="F" class="inp-form" />&nbsp;<label for="txtFemale">Female</label><br/>
                <input id="txtOther" name="txtGender" type="radio" value="Other" class="inp-form" />&nbsp;<label for="txtOther">Other</label><br/>
            </td>
            <td>
            <div class="error-left"></div>
            <div class="error-inner">This field is required.</div>
            </td>
        </tr>
        <tr>
        <th valign="top">Gender:</th>
        <td>    
        <select  class="styledselect_form_1">
            <option value="">All</option>
            <option value="">Products</option>
            <option value="">Categories</option>
            <option value="">Clients</option>
            <option value="">News</option>
        </select>
        </td>
        <td></td>
        </tr>
        <tr>
        <th valign="top">Sub Category:</th>
        <td>    
        <select  class="styledselect_form_1">
            <option value="">All</option>
            <option value="">Products</option>
            <option value="">Categories</option>
            <option value="">Clients</option>
            <option value="">News</option>
        </select>
        </td>
        <td></td>
        </tr> 
        <tr>
            <th valign="top">Price:</th>
            <td><input type="text" class="inp-form" /></td>
            <td></td>
        </tr>
        <tr>
        <th valign="top">Select a date:</th>
        <td class="noheight">
        
            <table border="0" cellpadding="0" cellspacing="0">
            <tr  valign="top">
                <td>
                <form id="chooseDateForm" action="#">
                
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
                </td>
                <td>
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
                </td>
                <td>
                    <select  id="y"  class="styledselect-year">
                        <option value="">yyyy</option>
                        <option value="2005">2005</option>
                        <option value="2006">2006</option>
                        <option value="2007">2007</option>
                        <option value="2008">2008</option>
                        <option value="2009">2009</option>
                        <option value="2010">2010</option>
                    </select>
                    </form>
                </td>
                <td><a href=""  id="date-pick"><img src="../images/forms/icon_calendar.jpg" alt="" /></a></td>
            </tr>
            </table>
        
        </td>
        <td></td>
    </tr>
    <tr>
        <th valign="top">Description:</th>
        <td><textarea rows="" cols="" class="form-textarea"></textarea></td>
        <td></td>
    </tr>
    <tr>
    <th>Image 1:</th>
    <td><input type="file" class="file_1" /></td>
    <td>
    <div class="bubble-left"></div>
    <div class="bubble-inner">JPEG, GIF 5MB max per image</div>
    <div class="bubble-right"></div>
    </td>
    </tr>
    <tr>
    <th>Image 2:</th>
    <td>  <input type="file" class="file_1" /></td>
    <td><div class="bubble-left"></div>
    <div class="bubble-inner">JPEG, GIF 5MB max per image</div>
    <div class="bubble-right"></div></td>
    </tr>
    <tr>
    <th>Image 3:</th>
    <td><input type="file" class="file_1" /></td>
    <td><div class="bubble-left"></div>
    <div class="bubble-inner">JPEG, GIF 5MB max per image</div>
    <div class="bubble-right"></div></td>
    </tr>
    <tr>
        <th>&nbsp;</th>
        <td valign="top">
            <input type="button" value="" class="form-submit" />
            <input type="reset" value="" class="form-reset"  />
        </td>
        <td></td>
    </tr>
    </table>
        </form>
    <!-- end id-form  -->

    </td>
    <td>

    <!--  start related-activities -->
    <div id="related-activities">
        
        <!--  start related-act-top -->
        <div id="related-act-top">
        <img src="../images/forms/header_related_act.gif" width="271" height="43" alt="" />
        </div>
        <!-- end related-act-top -->
        
        <!--  start related-act-bottom -->
        <div id="related-act-bottom">
        
            <!--  start related-act-inner -->
            <div id="related-act-inner">
            
                <div class="left"><a href=""><img src="../images/forms/icon_plus.gif" width="21" height="21" alt="" /></a></div>
                <div class="right">
                    <h5>Add another product</h5>
                    Lorem ipsum dolor sit amet consectetur
                    adipisicing elitsed do eiusmod tempor.
                    <ul class="greyarrow">
                        <li><a href="">Click here to visit</a></li> 
                        <li><a href="">Click here to visit</a> </li>
                    </ul>
                </div>
                
                <div class="clear"></div>
                <div class="lines-dotted-short"></div>
                
                <div class="left"><a href=""><img src="../images/forms/icon_minus.gif" width="21" height="21" alt="" /></a></div>
                <div class="right">
                    <h5>Delete products</h5>
                    Lorem ipsum dolor sit amet consectetur
                    adipisicing elitsed do eiusmod tempor.
                    <ul class="greyarrow">
                        <li><a href="">Click here to visit</a></li> 
                        <li><a href="">Click here to visit</a> </li>
                    </ul>
                </div>
                
                <div class="clear"></div>
                <div class="lines-dotted-short"></div>
                
                <div class="left"><a href=""><img src="../images/forms/icon_edit.gif" width="21" height="21" alt="" /></a></div>
                <div class="right">
                    <h5>Edit categories</h5>
                    Lorem ipsum dolor sit amet consectetur
                    adipisicing elitsed do eiusmod tempor.
                    <ul class="greyarrow">
                        <li><a href="">Click here to visit</a></li> 
                        <li><a href="">Click here to visit</a> </li>
                    </ul>
                </div>
                <div class="clear"></div>
                
            </div>
            <!-- end related-act-inner -->
            <div class="clear"></div>
        
        </div>
        <!-- end related-act-bottom -->
    
    </div>
    <!-- end related-activities -->

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
