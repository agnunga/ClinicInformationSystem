<%@page import="com.agunga.beans.Patient"%>
<%@page import="com.agunga.beans.Receptionist"%>
<%@page import="java.util.Iterator"%>
<form class="well form-horizontal" action="update_patient" method="post"
      id="contact_form">
    <fieldset>
        <!-- Form Name -->
        <legend>Update Patient Details</legend>
        <%
            Patient patient = new Receptionist().viewSinglePatientDetails(request.getParameter("id"));
        %>
        <div class="form-group">
            <label class="col-md-4 control-label">Patient ID: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-user"></i>
                    </span> 
                    <input name="patientId" placeholder="Patient ID" value="<%=patient.getPatientId()%>" class="form-control" readonly="readonly" type="text" required maxlength="10">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">National ID:</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input name="nationalId" placeholder="National ID" value="<%=patient.getNationalId()%>" class="form-control" readonly="readonly" type="text" required maxlength="8">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Patient Name:</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input name="name" placeholder="Patient Name" value="<%=patient.getName()%>" class="form-control" type="text" required maxlength="40">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Phone: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="patient_phone"
                                                                        placeholder="Phone" value="<%=patient.getPhone()%>"
                                                                        class="form-control" type="text" required maxlength="15">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Gender:</label>
            <div class="col-md-4">
                <span class="radio"> <label> <input type="radio"
                                                    name="sex" value="male" required
                                                    <%=(patient.getSex().equals("male") ? "checked" : "")%>/>
                        Male
                    </label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <label> <input
                            type="radio" name="sex" value="female" required
                            <%=(patient.getSex().equals("female") ? "checked" : "")%>/> Female
                    </label>
                </span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Date Of Birth: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="dob"
                                                                        placeholder="Date of Birth" value="<%=patient.getDob()%>"
                                                                        class="form-control" type="text" required maxlength="20">
                </div>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Update Patient's Details<span class=""></span>
                </button>
            </div>
        </div>

    </fieldset>
</form>
