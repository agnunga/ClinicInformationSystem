<%
    session = request.getSession(false);
    if (session != null && !session.isNew() && session.getAttribute("asession") != null) {
    } else {
        response.sendRedirect("start");
    }
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>CIS</title>
    </head>
    <meta charset="UTF-8">
    <title>Admin | CIS</title>
    <script src="http://s.codepen.io/assets/libs/modernizr.js"
    type="text/javascript"></script>
    <link rel='stylesheet prefetch'
          href='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet prefetch'
          href='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
    <link rel='stylesheet prefetch'
          href='http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
    <link rel="stylesheet" href="css/style.css">

    <link rel="stylesheet" href="/datatables/dataTables.bootstrap.css">
    <!-- Content Wrapper. Contains page content -->
</head>
<body>
    <div class="container">
        <div class="navbar navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/ClinicInformationSystem/">Clinic Information System</a><a
                        class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-collapse"> <span
                            class="glyphicon glyphicon-bar"></span> <span
                            class="glyphicon glyphicon-bar"></span> <span
                            class="glyphicon glyphicon-bar"></span>
                    </a>
                </div>
                <div class="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class=""><a href="/ClinicInformationSystem/admin/register_employee">Add Employee</a></li>
                        <li class=""><a href="/ClinicInformationSystem/admin/view_employees">View Employees</a></li>
                        <li class=""><a href="">Howdy! ${sessionScope.asession}</a></li>
                    </ul>
                </div>
                <!--/.navbar-collapse -->
            </div>
        </div>
        <div class="container">
            <div class="row">