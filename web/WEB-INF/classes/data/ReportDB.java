package data;

import java.util.*;
import javax.persistence.*;

import utility.DBUtil;
import business.ReportPK;
import business.Report;

public class ReportDB {

    public static List<Report> getAllReports() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select r from Report r";
        TypedQuery<Report> q = em.createQuery("Report.findAll", Report.class);
        List<Report> reports;
        try {
            reports = q.getResultList();
            if (reports == null || reports.isEmpty()) {
                reports = null;
            }
        } finally {
            em.close();
        }
        return reports;
    }

    public static Report getReportByIDs(String studyID, String questionID) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            ReportPK reportPK = new ReportPK();
            reportPK.setStudyID(studyID);
            reportPK.setQuestionID(questionID);
            Report report = em.find(Report.class, reportPK);
            return report;
        } finally {
            em.close();
        }
    }

    public static void addReport(Report report) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(report);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateReport(Report report) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(report);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
}
