
<%@page import="com.agunga.util.MyUtility"%>
<%@page import="com.agunga.model.Employee"%>
<%@page import="com.agunga.beanI.EmployeeBeanI"%>

<%
    EmployeeBeanI eb = (EmployeeBeanI) request.getAttribute("employeeBean");
    Employee employee = eb.viewEmployee(MyUtility.myParseLong(request.getParameter("id")));
%>
<form class="well form-horizontal" action="update_employee" method="post" id="contact_form">
    <fieldset> 
        <!-- Form Name -->
        <input type="hidden" name="id" readonly=""  value="<%= employee.getId()%>" required>

        <legend>Register Receptionist</legend> 
        <div class="form-group">
            <label class="col-md-4 control-label">Full Name: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i>
                    </span> 
                    <input name="name"   value="<%= employee.getName()%>"  class="form-control"  type="text" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">National ID: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> 
                    <input name="nationalId"
                           value="<%= employee.getNationalId()%>" class="form-control" type="text"
                           maxlength="8" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Phone: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span>
                    <input name="phone_no"
                           value="<%= employee.getPhone()%>" class="form-control" type="text" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Gender:</label>
            <div class="col-md-4">
                <span class="radio"> <label>
                        <input type="radio" name="sex" value="male" required /> Male
                    </label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <label>
                        <input type="radio" name="sex" value="female" required /> Female
                    </label>
                </span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Date Of Birth: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span>
                    <input name="dob"
                           value="<%= employee.getDob()%>" class="form-control" type="text"
                           required>
                </div>
            </div>
        </div>
        <hr />
        <div class="form-group">
            <label class="col-md-4 control-label">Employee No: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input name="employeeNo"
                           value="<%= employee.getEmployeeNo()%>" class="form-control" type="text"
                           required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Date Employed: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span>
                    <input name="dateEmployed"
                           value="<%= employee.getDateEmployed()%>" class="form-control" type="text"
                           required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Salary: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span>
                    <input name="salary"
                           value="<%= employee.getSalary()%>" class="form-control" type="text" required>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label">Role/Title: </label>
            <div class="col-md-4 selectContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-list"></i></span> 
                    <select name="title" required="" class="form-control selectpicker">
                        <option>-Select Role-</option>
                        <option value="r">Receptionist</option>
                        <option value="n">Nurse</option>
                        <option value="l">Lab Tech</option>
                        <option value="d">Doctor</option>
                        <option value="a">Administrator</option>
                    </select>
                </div>
            </div>
        </div>

        <hr />

        <!--		<div class="form-group">
                                <label class="col-md-4 control-label">Assignment: </label>
                                <div class="col-md-4 inputGroupContainer">
                                        <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-user"></i></span> <input name="assignment"
                                                        placeholder="Office assigned" class="form-control" type="text"
                                                        required>
                                        </div>
                                </div>
                        </div>-->
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Update Employee <span class="glyphicon glyphicon-send"></span>
                </button>
            </div>
        </div>

    </fieldset>
</form>
