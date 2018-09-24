<%-- 
    Document   : reporth
    Created on : Feb 5, 2016, 5:20:55 PM
    Author     : Abhishek Banerjee
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<br>
<div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Date</th>		
            <th>Status</th>

        </tr>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.question.questionID}</td>
                <td>${report.date}</td>
                <td>${report.RStatus}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>