<%@include file="header_rec.jsp" %>
<div class="col-md-2"></div>
<!--/span-->

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Receptionist</a></li>
			<li class="active">Patient Details</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<%@include file="viewPatientsTable.jsp"%>
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@include file="footer.jsp"%>