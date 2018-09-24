<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script src="js/reportques.js" type="text/javascript"></script>
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
-->
<div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Date</th>
            <th>Action</th>		
        </tr>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.question.questionID}</td>
                <td>${report.date}</td>
                <c:choose>
                    <c:when test="${report.RStatus != 'Pending'}">
                        <td>
                            ${report.RStatus}
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form action="studies" method="post">
                                <input type="hidden" name="studyID" value="${report.reportPK.studyID}">
                                <input type="hidden" name="action" value="">
                                <input type="submit" class="btn btn-primary" value="Approve" onclick="approve()">
                                <input type="submit" class="btn btn-primary" value="Dispprove" onclick="disapprove()">
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>


<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>