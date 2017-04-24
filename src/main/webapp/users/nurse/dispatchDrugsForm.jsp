<%@page import="com.agunga.beanI.NurseBeanI"%>
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%> 

<%
    NurseBeanI nbi = (NurseBeanI) request.getAttribute("nbi");
    Patient patient = nbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
%>
<form class="well form-horizontal" action="dispatch" method="post" id="contact_form">
    <fieldset>  
        <legend>Record drugs dispatch</legend> 
        Full name: <%= patient.getName()%> 
        National ID: <%= patient.getNationalId()%> 
        Phone: <%= patient.getPhone()%>
        Gender:
        Date Of Birth: <%= patient.getDob()%>
        <input name="id" value="<%= patient.getId()%>" type="hidden" required>
        <div class="form-group">
            <label class="col-md-4 control-label"> List dispatched drugs</label><br/>
            <div class="input-group">
                <div class="input-group"><textarea name="dispatch"></textarea></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">Save drug dispatch</button>
            </div>
        </div>
    </fieldset>
</form>
