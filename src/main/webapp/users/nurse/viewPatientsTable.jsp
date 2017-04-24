<%@page import="com.agunga.beanI.NurseBeanI"%>
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Patient"%>
<%@page import="com.agunga.beanI.ReceptionistBeanI"%>
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
                        <!--<th>National ID</th>-->
                        <th>Patient ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <!--<th>DoB</th>-->
                        <!--<th>Sex</th>-->
                        <th>Check in</th>
                        <th>Prescription</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String id = "";
                        if (request.getParameter("id") != null) {
                            id = request.getParameter("id");
                        }
                        NurseBeanI nbi = (NurseBeanI) request.getAttribute("nbi");
                        List<Patient> patients = nbi.viewPatients();
                        Iterator<Patient> iterator = patients.iterator();
                        while (iterator.hasNext()) {
                            Patient patient = iterator.next();
                            if (patient.getPrescription() != null && patient.getDrugs() == null) {
                    %>
                    <tr>
                         <td><%=patient.getPatientId()%></td>
                        <td><%=patient.getName()%></td>
                        <td><%=patient.getPhone()%></td> 
                        <td><%=patient.getCheckin()%></td>
                        <td><%=patient.getPrescription()%></td>
                        <td>
                            <a href="/ClinicInformationSystem/nurse/dispatch?id=<%=patient.getId()%>">
                                <button class="btn btn-warning btn-xs icon-edit"> Dispatch drugs </button>
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
