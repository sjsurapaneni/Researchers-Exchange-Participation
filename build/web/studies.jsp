<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
<%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
<%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >

    <div class="table-responsive">
        <table class="table" >
            <tr>
                <th>Study Name</th>
                <th>Requested Participants</th>     
                <th>Participations</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="study" items="${myStudies}">
                <tr>
                    <td>${study.studyName}</td>
                    <td>${study.reqParticipants}</td>
                    <td>${study.actParticipants}</td> 
                    <c:if test="${study.SStatus == 'Open'}">
                        <td>
                            <form action="studies" method="post">
                                <input type="hidden" name="action" value="stop" />
                                <input type="hidden" name="studyID" value="${study.studyID}" />
                                <button type="submit" class="btn btn-primary">Open</button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${study.SStatus == 'Closed'}">
                        <td>
                            <form action="studies" method="post">
                                <input type="hidden" name="action" value="start" />
                                <input type="hidden" name="studyID" value="${study.studyID}" />
                                <button type="submit" class="btn btn-primary">Closed</button>
                            </form>
                        </td>
                    </c:if>
                    <td>
                        <form action="studies" method="post">
                            <input type="hidden" name="action" value="edit" />
                            <input type="hidden" name="studyID" value="${study.studyID}" />
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <label>*Click on the status to modify it.</label>
    </div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>