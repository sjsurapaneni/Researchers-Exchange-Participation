<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/newstudy.js">
</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Adding a study</h3>
<%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to create new study --%>
<section>
    <form class="form-horizontal" action="studies" method="post">
        <input type="hidden" name="action" value="add"/>
        <div class="form-group">
            <label class="col-sm-4 control-label">Study ID *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="studyID" required />
            </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-4 control-label">Study Name *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="studyName" required />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Question Text *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="questionText" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Image *</label>
            <div class="col-sm-4">
                <input type="file" name="image"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-4 control-label"># Participants *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="participants" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Answer1</label>
            <div class="col-sm-4">
                <input name = "answer1" class="form-control" type="text" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Answer2</label>
            <div class="col-sm-4">
                <input name = "answer2" class="form-control" type="text" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Answer3</label>
            <div class="col-sm-4">
                <input name = "answer3" class="form-control" type="text" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Answer4</label>
            <div class="col-sm-4">
                <input name = "answer4" class="form-control" type="text" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Answer5</label>
            <div class="col-sm-4">
                <input name = "answer5" class="form-control" type="text" value=""/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Description *</label>
            <div class="col-sm-4">
                <textarea name="description" class="form-control" required></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-4">
                <button type="submit"  class="btn btn-primary">Submit</button>
                <br/><br/><br/>
            </div>
        </div>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>