<jsp:include page="assets/header.jsp"/>

<form class="well form-horizontal" action="config" method="post"
      id="contact_form" style="max-width: 900px; margin: 50px auto auto auto;">
    <fieldset>
        <legend style="text-align: center;">Welcome, Configure System</legend> 
        <div class="form-group">
            <label class="col-md-4 control-label">Select Database: </label>
            <div class="col-md-4 selectContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span> 
                    <select name="db" required="" class="form-control selectpicker">
                        <option value="m">MySQl</option>
                        <option value="o">Oracle</option> 
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">  Save <span class=""></span> </button>
            </div>
        </div>
    </fieldset>
</form>
<jsp:include page="assets/footer.jsp"/>