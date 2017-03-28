<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.agunga.beans.Patient"%>
<%@page import="com.agunga.beans.Receptionist"%>
<%@page import="java.util.Iterator"%>
<div class="col-xs-12">
    <div class="box">
        <h3>
            <%= ((request.getAttribute("updated") != null) ? request.getAttribute("updated") + " " : "")%>
        </h3>
        <div class="box-header">
            <h3 class="box-title">Patient Details</h3>
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
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Patient> patients = new Receptionist().viewPatientsDetails((Connection) request.getAttribute("mycon"));
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
                        <td>
                            <a href="/ClinicInformationSystem/receptionist/update_patient?id=<%=patient.getNationalId()%>">
                                <button class="btn btn-warning btn-xs icon-edit"> Edit </button>
                            </a>
                        </td>
                        <td>
                            <a href="/ClinicInformationSystem/receptionist/delete_patient?id=<%=patient.getNationalId()%>">
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
