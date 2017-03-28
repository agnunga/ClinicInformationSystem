<jsp:include page="header_rec.jsp"/>
<div class="col-md-8">
    <div class="container">
        <%=request.getAttribute("message")%>
        <%@include file="registerPatientForm.jsp"%>
    </div>
</div>
<jsp:include page="../../assets/footer.jsp"/>