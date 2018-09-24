/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "Report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByQuestionID", query = "SELECT r FROM Report r WHERE r.reportPK.questionID = :questionID"),
    @NamedQuery(name = "Report.findByStudyID", query = "SELECT r FROM Report r WHERE r.reportPK.studyID = :studyID"),
    @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date"),
    @NamedQuery(name = "Report.findByNumParticipants", query = "SELECT r FROM Report r WHERE r.numParticipants = :numParticipants"),
    @NamedQuery(name = "Report.findByRStatus", query = "SELECT r FROM Report r WHERE r.rStatus = :rStatus")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportPK reportPK;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "NumParticipants")
    private Integer numParticipants;
    @Column(name = "RStatus")
    private String rStatus;
    @JoinColumn(name = "QuestionID", referencedColumnName = "QuestionID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Question question;
    @JoinColumn(name = "StudyID", referencedColumnName = "StudyID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Study study;

    public Report() {
    }

    public Report(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Report(String questionID, String studyID) {
        this.reportPK = new ReportPK(questionID, studyID);
    }

    public ReportPK getReportPK() {
        return reportPK;
    }

    public void setReportPK(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(Integer numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getRStatus() {
        return rStatus;
    }

    public void setRStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportPK != null ? reportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportPK == null && other.reportPK != null) || (this.reportPK != null && !this.reportPK.equals(other.reportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.Report[ reportPK=" + reportPK + " ]";
    }
    
}
