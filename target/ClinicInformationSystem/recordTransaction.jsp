<jsp:include page="assets/header.jsp"/>
<form class="well form-horizontal" action="recordTransaction" method="post"
      id="contact_form" style="max-width: 900px; margin: 50px auto auto auto;">
    <fieldset>
        <legend style="text-align: center;">Record a transaction</legend>
        <% String error = (String) (request.getAttribute("login_error") != null ? request.getAttribute("login_error") : "");%>
        <h5 style="text-align: center; color: red;"><%=error%></h5>
        <div class="form-group">
            <label class="col-md-4 control-label">Amount : </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <input name="amount" placeholder="" class="form-control" type="text" required maxlength="10">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Type</label>
            <div class="col-md-4">
                <span class="radio"> <label> 
                        <input type="radio" name="type" value="cr" required /> Withdraw
                    </label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <label> 
                        <input type="radio" name="type" value="dr" required /> Deposit
                    </label>
                </span>
            </div>
        </div> 
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Record Transaction <span class=""></span>
                </button>
            </div>
        </div>

    </fieldset>
</form>
<jsp:include page="assets/footer.jsp"/>