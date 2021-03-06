<%@page import="com.agunga.beanI.LabtechBeanI"%>
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%> 
<%@page import="java.util.List"%>  
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
                        <!--<th>Sex</th>-->
                        <th>Diagnosis</th>
                        <th>Check in</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String id = "";
                        if (request.getParameter("id") != null) {
                            id = request.getParameter("id");
                        }
                        LabtechBeanI lbi = (LabtechBeanI) request.getAttribute("lbi");
                        List<Patient> patients = lbi.viewPatients();
                        Iterator<Patient> iterator = patients.iterator();
                        while (iterator.hasNext()) {
                            Patient patient = iterator.next();
                            if (patient.getDiagnosis() != null && patient.getTestResults() == null) {
                    %>
                    <tr>
                        <td><%=patient.getNationalId()%></td>
                        <td><%=patient.getPatientId()%></td>
                        <td><%=patient.getName()%></td>
                        <td><%=patient.getPhone()%></td>
                        <td><%=patient.getDob()%></td> 
                        <td><%=patient.getDiagnosis()%></td>
                        <td><%=patient.getCheckin()%></td>
                        <td>
                            <a href="/ClinicInformationSystem/lab/test?id=<%=patient.getId()%>">
                                <button class="btn btn-warning btn-xs icon-edit">Record test </button>
                            </a>
                        </td> 
                    </tr>
                    <%
                            }
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
