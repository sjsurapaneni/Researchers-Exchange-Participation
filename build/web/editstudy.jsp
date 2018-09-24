<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/editstudy.js">
</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="studies" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="studyID" value="${study.studyID}"/>
        <div class="form-group">
            <label class="col-sm-4 control-label">Study Name *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="studyName" value="${study.studyName}" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Question Text *</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="questionText" value="${question.question}" required/>
            </div>
        </div>


        <%-- Img tag is used to import image --%>
        <div class="form-group">
            <label class="col-sm-4 control-label">Image *</label>
            <div class="col-sm-4">
                <img src="${study.imageURL}" class="img-responsive" height="50" width="75" alt="Edit"/>
                <input type="file" name="file" />
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-4 control-label"># Participants *</label>
            <div class="col-sm-4"> 
                <input type="text" class="form-control" name="participants" value="${study.reqParticipants}" required/>
            </div>
        </div>

        <c:choose>
            <c:when test="${question.option1 != ''}">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer1</label>
                    <div class="col-sm-4">
                        <input name = "answer1" class="form-control" type="text" value="${question.option1}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer1</label>
                    <div class="col-sm-4">
                        <input name = "answer1" class="form-control" type="text" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${question.option2 != ''}">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer2</label>
                    <div class="col-sm-4">
                        <input name = "answer2" class="form-control" type="text" value="${question.option2}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer2</label>
                    <div class="col-sm-4">
                        <input name = "answer2" class="form-control" type="text" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${question.option3 != ''}">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer3</label>
                    <div class="col-sm-4">
                        <input name = "answer3" class="form-control" type="text" value="${question.option3}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer3</label>
                    <div class="col-sm-4">
                        <input name = "answer3" class="form-control" type="text" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${question.option4 != ''}">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer4</label>
                    <div class="col-sm-4">
                        <input name = "answer4" class="form-control" type="text" value="${question.option4}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer4</label>
                    <div class="col-sm-4">
                        <input name = "answer4" class="form-control" type="text" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${question.option1 != ''}">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer5</label>
                    <div class="col-sm-4">
                        <input name = "answer5" class="form-control" type="text" value="${question.option5}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Answer5</label>
                    <div class="col-sm-4">
                        <input name = "answer5" class="form-control" type="text" value=""/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="form-group">
            <label class="col-sm-4 control-label">Description *</label>
            <div class="col-sm-4"> 
                <textarea name="description" class="form-control" required>${study.description}</textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-4">
                <button type="submit"  class="btn btn-primary">Update</button>
            </div>
        </div>
        <br/><br/><br/>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>