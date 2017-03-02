<%@include file="header.jsp"%>
<%
%>
<form class="well form-horizontal" action="start" method="post"
	id="contact_form" style="max-width: 500px; margin: 50px auto auto auto;">
	<fieldset>
		<legend style="text-align: center;">Welcome, Login</legend>
		<% String error = (String)(request.getAttribute("login_error")  != null?request.getAttribute("login_error"):""); %>
		<h5 style="text-align: center; color: red;"><%=error%></h5>
		<div class="form-group">
			<label class="col-md-4 control-label">Employee No: </label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input name="employeeNo"
						placeholder="" class="form-control" type="text" required>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Password: </label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input name="password"
						placeholder="" class="form-control" type="password" required>
				</div>
			</div>
		</div>
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-4">
				<button type="submit" class="btn btn-warning">
					Sign In <span class=""></span>
				</button>
			</div>
		</div>

	</fieldset>
</form>
<%@include file="footer.jsp"%>