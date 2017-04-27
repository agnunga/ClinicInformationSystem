<%@page import="com.agunga.beanI.DoctorBeanI"%>
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%>
<%@page import="com.agunga.beanI.PatientBeanI"%>

<%
    DoctorBeanI dbi = (DoctorBeanI) request.getAttribute("dbi");
    Patient patient = dbi.viewPatient(MyUtility.myParseLong(request.getParameter("id")));
%>
<form class="well form-horizontal" action="diagnose" method="post" id="contact_form">
    <fieldset>  
        <legend>Record diagnosis</legend> 
        Full name: 
        <%= patient.getName()%> 
        Patient ID: 
        <%= patient.getPatientId()%>
        National ID: 
        <%= patient.getNationalId()%> 
        Phone: 
        <%= patient.getPhone()%>
        Gender:
        <%= patient.getSex()%>
        Date Of Birth: 
        <%= patient.getDob()%>
        <input name="id" value="<%= patient.getId()%>"type="hidden" required>
        <div class="form-group">
            <label class="control-label">Diagnosis</label><br/>
            <div class="input-group">
                <div class="input-group"><textarea name="diagnosis"></textarea></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">Save diagnosis</button>
            </div>
        </div>
    </fieldset>
</form>
