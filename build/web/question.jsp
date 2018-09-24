<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${theUser.coins}</span>) </li>
        <li>Participants (<span class="count">${theUser.studies}</span>) </li>
        <li>Participation (<span class="count">${theUser.participation}</span>) </li>
        <li><br></li>
        <li><a href="home.jsp">Home</a></li>
        <li><a href="studies?action=participate">Participate</a></li>
        <li><a href="studies?action=studies">My Studies</a></li>
        <li><a href="recommend.jsp">Recommend</a></li>
        <li><a href="contact.jsp">Contact</a></li>
    </ul>
</nav>
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="${participateStudy.imageURL}" class="img-responsive" height="250" width="250" alt="Image"/>

    <%--Code to rating the Question --%>
    <p class="text-left">${studyQuestion.question}</p>

    <form action="studies" method="post">
        <input type="hidden" name="questionID" value="${studyQuestion.questionID}">
        <input type="hidden" name="studyID" value="${participateStudy.studyID}">
        <input type="hidden" name="action" value="answer">
        <c:if test="${studyQuestion.option1 != ''}">
            <div class="radio">
                <input type="radio" name="number" value="${studyQuestion.option1}" required>${studyQuestion.option1}
            </div>
        </c:if>
        <c:if test="${studyQuestion.option2 != ''}">
            <div class="radio">
                <input type="radio" name="number" value="${studyQuestion.option2}" required>${studyQuestion.option2}
            </div>
        </c:if>
        <c:if test="${studyQuestion.option3 != ''}">
            <div class="radio">
                <input type="radio" name="number" value="${studyQuestion.option3}" required>${studyQuestion.option3}
            </div>
        </c:if>
        <c:if test="${studyQuestion.option4 != ''}">
            <div class="radio">
                <input type="radio" name="number" value="${studyQuestion.option4}" required>${studyQuestion.option4}
            </div>
        </c:if>
        <c:if test="${studyQuestion.option5 != ''}">
            <div class="radio">
                <input type="radio" name="number" value="${studyQuestion.option5}" required>${studyQuestion.option5}
            </div>
        </c:if>

        <%-- Code to submit the Rating  --%>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
                <button type="submit"  class="btn btn-primary">Submit</button>
            </div>
        </div>
        <br/><br/><br/>   
    </form>


</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>