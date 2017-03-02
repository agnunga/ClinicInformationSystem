<%@page import="com.agunga.cis.Patient"%>
<%@page import="com.agunga.cis.Receptionist"%>
<%@page import="java.util.Iterator"%>
<div class="col-xs-12">
	<div class="box">
		<h3>
			<%= ((request.getAttribute("updated")!=null)?request.getAttribute("updated")+" ":"") %>
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
						<th>Check out</th>
					</tr>
				</thead>
				<tbody>
					<%
						Iterator<Patient> iterator = new Receptionist().viewPatientsDetails().iterator();
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
						<td><%=patient.getCheckout()%></td>
						<td><a href="update_patient?id=<%=patient.getNationalId()%>"><button
									class="btn btn-warning btn-xs icon-edit">Edit</button></a></td>
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
		<!-- /.box-body -->
	</div>
	<!-- /.box -->
</div>
<!-- /.col -->
</div>
