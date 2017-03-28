<jsp:include page="assets/header.jsp"/>    
<form class="well form-horizontal" action="upload_file3" method="post" enctype="multipart/form-data" id="contact_form" style="max-width: 700px; margin: 50px auto auto auto;">
    <fieldset>
        <legend style="text-align: center;">File Upload</legend> 
        <div class="form-group">
            <label class="col-md-4 control-label">Upload File: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                    <input name="file" placeholder="" class="" type="file" required>
                </div>
            </div>
        </div> 
        
        <img src="file://D:/temp/Screenshot.png" width="200" alt="i"/>

        <input name="destination" value="D:\temp" class="form-control" type="text" required>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <input type="submit" class="btn btn-warning" value="Sign In" >
            </div>
        </div>
    </fieldset>
</form>
<jsp:include page="assets/footer.jsp"/>