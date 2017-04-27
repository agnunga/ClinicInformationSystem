<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%> 
<%@page import="java.util.List"%>  
<%@page import="java.util.Iterator"%>
<div class="col-xs-12">

    <form class="well form-inline" action="view_patients" method="post" id="contact_form">
        <fieldset> 
            <div class="input-group" style="text-align: right;"> 
                <label class="control-label">Search by Patient ID: </label>  
                <input name="patientId" placeholder="Patient ID" class="form-control" type="text" required maxlength="10">
                <!--<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-search"> </span></button>-->
            </div> 
        </fieldset>
    </form> 

    <div class="box">
        <h3>
            <%= ((request.getAttribute("updated") != null) ? request.getAttribute("updated") + " " : "")%>
            <%= ((request.getAttribute("message") != null) ? request.getAttribute("message") + " " : "")%>
        </h3>
        <div class="box-header">
            <!--<h3 class="box-title">Patient Details</h3>-->
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>National ID</th>
                        <th>Patient ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>DoB</th>
                        <th>Sex</th>
                        <th>Check in</th>
                        <th>Registered by</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Patient> patients = (List) request.getAttribute("patients");
                        Iterator<Patient> iterator = patients.iterator();
                        while (iterator.hasNext()) {
                            Patient patient = iterator.next();
                    %>
                    <tr>
                        <td><%=patient.getNationalId()%></td>
                        <td><%=patient.getPatientId()%></td>
                        <td><%=patient.getName()%></td>
                        <td><%=patient.getPhone()%></td>
                        <td><%=patient.getDob()%></td>
                        <td><%=patient.getSex()%></td>
                        <td><%=patient.getCheckin()%></td>
                        <td><%=patient.getAddedBy()%></td>
                        <td>
                            <a href="/ClinicInformationSystem/receptionist/update_patient?id=<%=patient.getId()%>">
                                <button class="btn btn-warning btn-xs icon-edit"> Edit </button>
                            </a>
                        </td>
                        <td>
                            <a href="/ClinicInformationSystem/receptionist/delete_patient?id=<%=patient.getId()%>">
                                <button class="btn btn-danger btn-xs icon-edit"> Delete </button>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
                <tfoot>
                <str>
                    </tr>
                    </tfoot>
            </table>
        </div>
    </div>
</div>
