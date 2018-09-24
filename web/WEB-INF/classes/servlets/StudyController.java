package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import business.User;
import business.Study;
import business.Question;
import business.Report;
import business.Answer;
import business.AnswerPK;
import business.ReportPK;
import data.ReportDB;
import data.StudyDB;
import data.UserDB;

@WebServlet(name = "StudyController", urlPatterns = {"/studies"})
public class StudyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = null;
        String action = request.getParameter("action");
        if (action == null) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else if (session.getAttribute("theAdmin") != null) {
                url = "/admin.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if (action.equals("participate")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                if (studyID == null) {
                    List<Study> openStudies = StudyDB.getOpenStudies();
                    url = "/participate.jsp";
                    request.setAttribute("openStudies", openStudies);
                } else {
                    Study participateStudy = StudyDB.getStudyByStudyID(studyID);
                    Question studyQuestion = StudyDB.getQuestionByStudyID(studyID);
                    url = "/question.jsp";
                    request.setAttribute("participateStudy", participateStudy);
                    request.setAttribute("studyQuestion", studyQuestion);
                }
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("edit")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                Study study = StudyDB.getStudyByStudyID(studyID);
                Question question = StudyDB.getQuestionByStudyID(studyID);
                url = "/editstudy.jsp";
                request.setAttribute("study", study);
                request.setAttribute("question", question);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("report")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                if (studyID == null) {
                    List<Report> reports = ReportDB.getAllReports();
                    url = "/reporth.jsp";
                    request.setAttribute("reports", reports);
                } else {
                    String questionID = studyID + "001";
                    int actParticipants = Integer.parseInt(request.getParameter("actParticipants"));
                    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                    ReportPK reportPK = new ReportPK();
                    reportPK.setStudyID(studyID);
                    reportPK.setQuestionID(questionID);
                    Report report = new Report();
                    report.setReportPK(reportPK);
                    report.setDate(date);
                    report.setNumParticipants(actParticipants);
                    report.setRStatus("Pending");
                    ReportDB.addReport(report);
                    url = "/confirmrep.jsp";
                }
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("approve")) {
            if (session.getAttribute("theAdmin") != null) {
                String studyID = request.getParameter("studyID");
                String questionID = studyID + "001";
                Report report = ReportDB.getReportByIDs(studyID, questionID);
                report.setRStatus("Approved");
                ReportDB.updateReport(report);
                List<Report> reports = ReportDB.getAllReports();
                url = "/reportques.jsp";
                request.setAttribute("reports", reports);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("disapprove")) {
            if (session.getAttribute("theAdmin") != null) {
                String studyID = request.getParameter("studyID");
                String questionID = studyID + "001";
                Report report = ReportDB.getReportByIDs(studyID, questionID);
                report.setRStatus("Disapproved");
                ReportDB.updateReport(report);
                List<Report> reports = ReportDB.getAllReports();
                url = "/reportques.jsp";
                request.setAttribute("reports", reports);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("update")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                String studyName = request.getParameter("studyName");
                String questionText = request.getParameter("questionText");
                int participants = Integer.parseInt(request.getParameter("participants"));
                String answer1 = request.getParameter("answer1");
                String answer2 = request.getParameter("answer2");
                String answer3 = request.getParameter("answer3");
                String answer4 = request.getParameter("answer4");
                String answer5 = request.getParameter("answer5");
                String description = request.getParameter("description");
                Study study = StudyDB.getStudyByStudyID(studyID);
                study.setStudyName(studyName);
                study.setReqParticipants(participants);
                study.setDescription(description);
                Question question = StudyDB.getQuestionByStudyID(studyID);
                question.setQuestion(questionText);
                if (!"".equals(answer1)) {
                    question.setOption1(answer1);
                } else {
                    question.setOption1("");
                }
                if (!"".equals(answer2)) {
                    question.setOption2(answer2);
                } else {
                    question.setOption2("");
                }
                if (!"".equals(answer3)) {
                    question.setOption3(answer3);
                } else {
                    question.setOption3("");
                }
                if (!"".equals(answer4)) {
                    question.setOption4(answer4);
                } else {
                    question.setOption4("");
                }
                if (!"".equals(answer5)) {
                    question.setOption5(answer5);
                } else {
                    question.setOption5("");
                }
                StudyDB.updateStudy(study);
                StudyDB.updateQuestion(question);
                User sessionUser = (User) session.getAttribute("theUser");
                String currentUserEmail = sessionUser.getUseremail();
                List<Study> myStudies = StudyDB.getStudiesByCreatorEmail(currentUserEmail);
                url = "/studies?action=studies";
                request.setAttribute("myStudies", myStudies);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("add")) {
            if (session.getAttribute("theUser") != null) {
                User sessionUser = (User) session.getAttribute("theUser");
                String currentUserEmail = sessionUser.getUseremail();
                String studyID = request.getParameter("studyID");
                String studyName = request.getParameter("studyName");
                String questionText = request.getParameter("questionText");
                int participants = Integer.parseInt(request.getParameter("participants"));
                String answer1 = request.getParameter("answer1");
                String answer2 = request.getParameter("answer2");
                String answer3 = request.getParameter("answer3");
                String answer4 = request.getParameter("answer4");
                String answer5 = request.getParameter("answer5");
                String description = request.getParameter("description");
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                Study study = new Study();
                study.setStudyID(studyID);
                study.setStudyName(studyName);
                study.setDescription(description);
                study.setUseremail(sessionUser);
                study.setDateCreated(date);
                study.setImageURL("images/004.jpg");
                study.setReqParticipants(participants);
                study.setActParticipants(0);
                study.setSStatus("Closed");
                Question question = new Question();
                String questionID = studyID + "001";
                question.setQuestionID(questionID);
                question.setStudyID(study);
                question.setQuestion(questionText);
                question.setAnswerType("Text");
                question.setOption1(answer1);
                question.setOption2(answer2);
                question.setOption3(answer3);
                question.setOption4(answer4);
                question.setOption5(answer5);
                StudyDB.addStudy(study);
                StudyDB.addQuestin(question);
                List<Study> myStudies = StudyDB.getStudiesByCreatorEmail(currentUserEmail);
                url = "/studies.jsp";
                request.setAttribute("myStudies", myStudies);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("start")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                Study study = StudyDB.getStudyByStudyID(studyID);
                study.setSStatus("Open");
                StudyDB.updateStudy(study);
                User sessionUser = (User) session.getAttribute("theUser");
                String currentUserEmail = sessionUser.getUseremail();
                List<Study> myStudies = StudyDB.getStudiesByCreatorEmail(currentUserEmail);
                url = "/studies.jsp";
                request.setAttribute("myStudies", myStudies);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("stop")) {
            if (session.getAttribute("theUser") != null) {
                String studyID = request.getParameter("studyID");
                Study study = StudyDB.getStudyByStudyID(studyID);
                study.setSStatus("Closed");
                StudyDB.updateStudy(study);
                User sessionUser = (User) session.getAttribute("theUser");
                String currentUserEmail = sessionUser.getUseremail();
                List<Study> myStudies = StudyDB.getStudiesByCreatorEmail(currentUserEmail);
                url = "/studies.jsp";
                request.setAttribute("myStudies", myStudies);
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("answer")) {
            if (session.getAttribute("theUser") != null) {
                User sessionUser = (User) session.getAttribute("theUser");
                String currentUserEmail = sessionUser.getUseremail();
                String studyID = request.getParameter("studyID");
                String questionID = request.getParameter("questionID");
                String choice = request.getParameter("number");
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                if (studyID != null) {
                    AnswerPK answerPK = new AnswerPK();
                    answerPK.setQuestionID(questionID);
                    answerPK.setStudyID(studyID);
                    answerPK.setUseremail(currentUserEmail);
                    Answer answer = new Answer();
                    answer.setAnswerPK(answerPK);
                    answer.setChoice(choice);
                    answer.setDateSubmitted(date);
                    answer.setUser(sessionUser);
                    StudyDB.addAnswerPK(answerPK);
                    StudyDB.addAnswer(answer);
                    User user = UserDB.getUserByEmail(currentUserEmail);
                    int coins = user.getCoins() + 1;
                    int participation = user.getParticipation() + 1;
                    user.setCoins(coins);
                    user.setParticipation(participation);
                    UserDB.updateUser(user);
                    session.setAttribute("theUser", user);
                    Study study = StudyDB.getStudyByStudyID(studyID);
                    int participants = study.getActParticipants() + 1;
                    study.setActParticipants(participants);
                    StudyDB.updateStudy(study);
                    List<Study> openStudies = StudyDB.getOpenStudies();
                    url = "/participate.jsp";
                    request.setAttribute("openStudies", openStudies);
                }
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("studies")) {
            if (session.getAttribute("theUser") != null){
            User sessionUser = (User) session.getAttribute("theUser");
            String currentUserEmail = sessionUser.getUseremail();
            List<Study> myStudies = StudyDB.getStudiesByCreatorEmail(currentUserEmail);
            url = "/studies.jsp";
            request.setAttribute("myStudies", myStudies);
            } else{
                url = "/login.jsp";
            }
        } else if (action.equals("reportList")) {
            if (session.getAttribute("theAdmin") != null) {
                List<Report> reports = ReportDB.getAllReports();
                url = "/reportques.jsp";
                request.setAttribute("reports", reports);
            } else {
                url = "/login.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
