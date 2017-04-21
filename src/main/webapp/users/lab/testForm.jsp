<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%>
<%@page import="com.agunga.beanI.PatientBeanI"%>

<%
    PatientBeanI eb = (PatientBeanI) request.getAttribute("employeeBean");
    Patient employee = eb.viewById(MyUtility.myParseLong(request.getParameter("id")));
%>
<form class="well form-horizontal" action="update_employee" method="post" id="contact_form">
    <fieldset>  
        <legend>Record test results</legend> 

        Full name: <%= employee.getName()%> 
        National ID: <%= employee.getNationalId()%> 
        Phone: <%= employee.getPhone()%>
        Gender:
        Date Of Birth: <%= employee.getDob()%>

        <div class="form-group">
            <label class="col-md-4 control-label">Patient ID: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input name="employeeNo"
                           value="<%= employee.getPatientId()%>" class="form-control" type="text" required>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label">Test result </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <textarea name="test">
                        
                    </textarea>   
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Save diagnosis <span class="glyphicon glyphicon-send"></span>
                </button>
            </div>
        </div>
    </fieldset>
</form>
