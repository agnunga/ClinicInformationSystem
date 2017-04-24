<%@page import="javax.ejb.EJB"%>
<%@page import="com.agunga.model.Employee"%>
<%@page import="com.agunga.beanI.EmployeeBeanI"%>
<%@page import="java.util.Iterator"%>
<div class="col-xs-12"> 
    <div class="box-body">
        <table id="example1" class="table table-bordered table-striped">
            <thead>
                <%
                    if (request.getAttribute("updated") != null) {
                        out.print("Message: " + request.getAttribute("updated"));
                    }
                %>
                <tr>
                    <th>National ID</th>
                    <th>Employee No</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>D o B</th>
                    <th>Sex</th>
                    <th>Role</th>
                    <th>Date Employed</th>
                    <th>Added By</th>
                </tr>
            </thead>
            <tbody>  
                <%    
                    EmployeeBeanI eb = (EmployeeBeanI) request.getAttribute("employeeBean");
                    if (eb == null) {
                        out.print(null + " Null eb");
                    } else {
                        Iterator<Employee> iterator = eb.viewEmployees().iterator();
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
                    <td><%=e.getAddedBy()%></td>
                    <td>
                        <a href="update_employee?id=<%=e.getId()%>">
                            <button class="btn btn-warning btn-xs icon-edit">Edit</button>
                        </a>
                    </td>
                    <td>
                        <a href="delete_employee?id=<%=e.getId()%>">
                            <button class="btn btn-danger btn-xs icon-trash">Delete</button>
                        </a>
                    </td>
                </tr>
                <%
                        }
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
