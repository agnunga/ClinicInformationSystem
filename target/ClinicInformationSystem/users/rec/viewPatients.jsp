<jsp:include page="header_rec.jsp" />
<div class="col-md-2"></div>
<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Receptionist</a></li>
            <li class="active">Patient Details</li>
        </ol>
    </section>
    <section class="content">
        <div class="row">
            <jsp:include page="viewPatientsTable.jsp"/>
        </div>
    </section>
</div>
<jsp:include page="../../assets/footer.jsp"/>