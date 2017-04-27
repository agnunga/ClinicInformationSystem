<%@include file="../assets/header.jsp"%> 
<form class="well form-horizontal" action="upload_file" method="post" enctype="multipart/form-data"
      id="contact_form" style="max-width: 500px; margin: 50px auto auto auto;">
    <fieldset>
        <legend style="text-align: center;">Upload file</legend>
        <% String error = (String) (request.getAttribute("upload_error") != null ? request.getAttribute("login_error") : "");%>
        <h5 style="text-align: center; color: red;"><%=error%></h5>
        <div class="form-group">
            <label class="col-md-4 control-label">File : </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group"> 
                    <input name="myfile" placeholder="" class="" type="file" required>
                </div>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Upload <span class=""></span>
                </button>
            </div>
        </div>
    </fieldset>
</form>
<%@include file="footer.jsp"%>