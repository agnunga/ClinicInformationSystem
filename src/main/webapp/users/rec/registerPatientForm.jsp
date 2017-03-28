<form class="well form-horizontal" action="register_patient" method="post"
      id="contact_form">
    <fieldset>

        <!-- Form Name -->
        <legend>Register Patient</legend>


        <div class="form-group">
            <label class="col-md-4 control-label">Patient Name:</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="name"
                                                                        placeholder="Patient Name" class="form-control" type="text"
                                                                        required maxlength="40">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Patient National ID:</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="nationalId"
                                                                        placeholder="National ID" class="form-control" type="text"
                                                                        required maxlength="8">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Phone: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="patient_phone"
                                                                        placeholder="Phone" class="form-control" type="text" required
                                                                        maxlength="15">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Gender:</label>
            <div class="col-md-4">
                <span class="radio"> <label> <input type="radio"
                                                    name="sex" value="male" required /> Male
                    </label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <label> <input
                            type="radio" name="sex" value="female" required /> Female
                    </label>
                </span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Date Of Birth: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="dob" placeholder="Date of Birth" class="form-control" type="text"
                                                                        required maxlength="20">
                </div>
            </div>
        </div>
        <hr />
        <div class="form-group">
            <label class="col-md-4 control-label">Assign Patient ID: </label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i
                            class="glyphicon glyphicon-user"></i></span> <input name="patientId"
                                                                        placeholder="Patient ID" class="form-control" type="text" required
                                                                        maxlength="10">
                </div>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning">
                    Register Patient <span class="glyphicon glyphicon-send"></span>
                </button>
            </div>
        </div>

    </fieldset>
</form>
