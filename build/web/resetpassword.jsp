<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<%--Code to signup form --%>
<section>
    <br/><br/><br/>
    <form class="form-horizontal" action="users" method="post">
        <div class="form-group">
            <label class="col-sm-4 control-label" ></label>
            <div class="col-sm-4">
                <label>${errorMsg}</label>
            </div>
        </div>
        <input type="hidden" name="action" value="resetpassword" />
        <input type="hidden" name="token" value ="${param.token}"/>
        <div class="form-group">
            <label class="col-sm-4 control-label" >Email Address *</label>
            <div class="col-sm-4">
                <input type="email"  class="form-control" name="email" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Password *</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="password" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Confirm Password *</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="confirm_password" required />
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5">
                <input type="submit" value="Reset Password" class="btn btn-primary">
            </div>
        </div>
        <br><br/><br/>
    </form>
</section>

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>