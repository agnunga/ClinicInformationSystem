<%@page import="javax.ejb.EJB"%>
<%@page import="com.agunga.beansI.EmployeeBeanI"%>
<%@page import="com.agunga.models.Employee"%> 
<%@page import="java.util.Iterator"%>
<div class="col-xs-12"> 
    <div class="box-body">
        <table id="example1" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>National ID</th>
                    <th>Employee No</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>D o B</th>
                    <th>Sex</th>
                    <th>Role</th>
                    <th>Date Employed</th>
                </tr>
            </thead>
            <tbody> 
                <%
                    EmployeeBeanI eb = (EmployeeBeanI) request.getAttribute("employeeBean");
                    Iterator<Employee> iterator = eb.view().iterator();
                    while (iterator.hasNext()) {
                        Employee e = iterator.next();
                %>
                <tr>
                    <td><%=e.getNationalId()%></td>
                    <td><%=e.getEmployeeNo()%></td>
                    <td><%=e.getName()%></td>
                    <td><%=e.getPhone()%></td>
                    <td><%=e.getDob()%></td>
                    <td><%=e.getSex()%></td>
                    <td><%=e.getTitle()%></td>
                    <td><%=e.getDateEmployed()%></td>
                    <td>
                        <a href="update_employee?id=<%=e.getNationalId()%>">
                            <button class="btn btn-warning btn-xs icon-edit">Edit</button>
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
            <tfoot>
                <tr>
                </tr>
            </tfoot>
        </table>
    </div>
</div>
