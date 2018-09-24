package data;

import java.util.*;
import javax.persistence.*;

import utility.DBUtil;
import business.Study;
import business.Question;
import business.AnswerPK;
import business.Answer;

public class StudyDB {

    public static List<Study> getAllStudies() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select s from Study s";
        TypedQuery<Study> q = em.createQuery(sqlQuery, Study.class);
        //TypedQuery<Study> namedQuery = em.createNamedQuery("Study.findAll", Study.class);
        List<Study> studies;
        try {
            studies = q.getResultList();
            if (studies == null || studies.isEmpty()) {
                studies = null;
            }
        } finally {
            em.close();
        }
        return studies;
    }

    public static Study getStudyByStudyID(String studyID) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            Study study = em.find(Study.class, studyID);
            return study;
        } finally {
            em.close();
        }
    }

    public static List<Study> getStudiesByCreatorEmail(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select s from Study s where s.useremail.useremail = " + "'" + email + "'";
        TypedQuery<Study> q = em.createQuery(sqlQuery, Study.class);
        List<Study> studies;
        try {
            studies = q.getResultList();
            if (studies == null || studies.isEmpty()) {
                studies = null;
            }
        } finally {
            em.close();
        }
        return studies;
    }

    public static List<Study> getOpenStudies() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select s from Study s where s.sStatus = 'Open'";
        TypedQuery<Study> q = em.createQuery(sqlQuery, Study.class);
        List<Study> studies;
        try {
            studies = q.getResultList();
            if (studies == null || studies.isEmpty()) {
                studies = null;
            }
        } finally {
            em.close();
        }
        return studies;
    }

    public static void addStudy(Study study) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(study);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateStudy(Study study) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(study);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static Question getQuestionByStudyID(String studyID) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String questionID = studyID + "001";
        try {
            Question question = em.find(Question.class, questionID);
            return question;
        } finally {
            em.close();
        }
    }

    public static void addQuestin(Question question) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(question);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateQuestion(Question question) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(question);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void addAnswerPK(AnswerPK answerPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(answerPK);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void addAnswer(Answer answer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(answer);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
//
//    public static void startStudy(ArrayList<Study> studies, String studyCode) {
//        int i = studies.size();
//        for (int j = 0; j < i; j++) {
//            String currentStudyCode = studies.get(j).getStudyCode();
//            if (currentStudyCode.equals(studyCode)) {
//                studies.get(j).setStatus("Start");
//            }
//        }
//    }
//
//    public static void stopStudy(ArrayList<Study> studies, String studyCode) {
//        int i = studies.size();
//        for (int j = 0; j < i; j++) {
//            String currentStudyCode = studies.get(j).getStudyCode();
//            if (currentStudyCode.equals(studyCode)) {
//                studies.get(j).setStatus("Stop");
//            }
//        }
//    }
//
//    public static void updateStudyParticipation(ArrayList<Study> studies, String studyCode) {
//        int i = studies.size();
//        for (int j = 0; j < i; j++) {
//            String currentStudyCode = studies.get(j).getStudyCode();
//            if (currentStudyCode.equals(studyCode)) {
//                studies.get(j).setNumOfParticipants(studies.get(j).getNumOfParticipants() + 1);
//            }
//        }
//    }
//
//    public static ArrayList<Report> defaultReports() {
//        ArrayList<Report> reports = new ArrayList<>();
//        //
//        Report report1 = new Report();
//        report1.setReportID("101");
//        report1.setReporterEmail("mike@gmail.com");
//        report1.setStudyCode("001");
//        report1.setQuestion("Question is too simple?");
//        report1.setDateCreated("02/01/2016");
//        report1.setStatus("Approved");
//        reports.add(report1);
//        //
//        Report report2 = new Report();
//        report2.setReportID("102");
//        report2.setReporterEmail("peter@gmail.com");
//        report2.setStudyCode("002");
//        report2.setQuestion("Can this question be more specific?");
//        report2.setDateCreated("02/02/2016");
//        report2.setStatus("Approved");
//        reports.add(report2);
//        //
//        Report report3 = new Report();
//        report3.setReportID("103");
//        report3.setReporterEmail("peter@gmail.com");
//        report3.setStudyCode("003");
//        report3.setQuestion("The answer is 1 right?");
//        report3.setDateCreated("02/02/2016");
//        report3.setStatus("Approved");
//        reports.add(report3);
//        //
//        Report report4 = new Report();
//        report4.setReportID("104");
//        report4.setReporterEmail("peter@gmail.com");
//        report4.setStudyCode("003");
//        report4.setQuestion("Need more options?");
//        report4.setDateCreated("02/03/2016");
//        report4.setStatus("Pending");
//        reports.add(report4);
//
//        return reports;
//    }
//
//    public static ArrayList<Report> getReports(ArrayList<Report> reports, String reporterEmail) {
//        ArrayList<Report> qualifiedReports = new ArrayList<>();
//        int i = reports.size();
//        for (int j = 0; j < i; j++) {
//            String currentReporterEmails = reports.get(j).getReporterEmail();
//            if (currentReporterEmails.equals(reporterEmail)) {
//                qualifiedReports.add(reports.get(j));
//            }
//        }
//        return qualifiedReports;
//    }
//
//    public static void approveReport(ArrayList<Report> reports, String reportID) {
//        int i = reports.size();
//        for (int j = 0; j < i; j++) {
//            String currentReportID = reports.get(j).getReportID();
//            if (currentReportID.equals(reportID)) {
//                reports.get(j).setStatus("Approved");
//            }
//        }
//    }
//
//    public static void disapproveReport(ArrayList<Report> reports, String reportID) {
//        int i = reports.size();
//        for (int j = 0; j < i; j++) {
//            String currentReportID = reports.get(j).getReportID();
//            if (currentReportID.equals(reportID)) {
//                reports.get(j).setStatus("Disapproved");
//            }
//        }
//    }
//
//    public static void addReport(ArrayList<Report> reports, Report report) {
//        reports.add(report);
//    }
}
