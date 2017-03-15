<form class="well form-horizontal" action="exercise.jsp" method="post">
    <div class="form-group">
        <label class="col-md-4 control-label">name:</label>
        <input name="name" class="form-control" type="text"  required maxlength="40">
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Email</label>
        <input name="email" class="form-control" type="text"  required maxlength="40">
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Password</label>
        <input name="password" class="form-control" type="password"  required maxlength="40">
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-4">
            <button type="submit" class="btn btn-warning">
                Send <span class="glyphicon glyphicon-send"></span>
            </button>
        </div>
    </div>
</form>