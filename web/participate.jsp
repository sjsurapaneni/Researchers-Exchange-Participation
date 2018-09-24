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
<%-- Section to display studies and participate in that study--%>
<div>

    <h3 class="text-left"><span class="label label-default " >Studies</span>
        <span ><a class="label label-default" href="studies?action=report">Report History</a></span></h3>
</div>

<%-- Display the studies in the table --%>
<%-- Clicking on Participate button displays Question.jsp page where 
        you can rate the question--%>
<div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
        <c:forEach var="study" items="${openStudies}">
            <tr>
                <td>${study.studyName}</td>
                <td><img src="${study.imageURL}" alt="Image"></td>
                <td>${study.description}</td> 
                <td>
                    <form action="studies" method="Post">
                        <input type="hidden" name="studyID" value="${study.studyID}">
                        <input type="hidden" name="action" value="participate">
                        <input type="submit" class="participate_button" value="participate" />
                    </form>
                </td>
                <td>
                    <form action="studies" method="Post">
                        <input type="hidden" name="studyID" value="${study.studyID}">
                        <input type="hidden" name="actParticipants" value="${study.actParticipants}">
                        <input type="hidden" name="action" value="report">
                        <input type="submit" class="participate_button" value="Report" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>