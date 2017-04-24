<%@page import="com.agunga.beanI.LabtechBeanI"%>
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%> 

<%
    LabtechBeanI lbi = (LabtechBeanI) request.getAttribute("lbi");
    Patient patient = lbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
%>
<form class="well form-horizontal" action="test" method="post" id="contact_form">
    <fieldset>  
        <legend>Record test results</legend> 
        Full name: <%= patient.getName()%> 
        National ID: <%= patient.getNationalId()%> 
        Phone: <%= patient.getPhone()%>
        Gender:
        Date Of Birth: <%= patient.getDob()%>
        <input name="id" value="<%= patient.getId()%>" type="hidden" required>
        <div class="form-group">
            <label class="col-md-4 control-label">Weight: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input name="weight" value="" class="form-control" type="text" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Blood Type: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input name="blood" value="" class="form-control" type="text" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">record test result</label><br/>
            <div class="input-group">
                <div class="input-group"><textarea name="test"></textarea></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">Save test results</button>
            </div>
        </div>
    </fieldset>
</form>
