/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author apple
 */
@Embeddable
public class ReportPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "QuestionID")
    private String questionID;
    @Basic(optional = false)
    @Column(name = "StudyID")
    private String studyID;

    public ReportPK() {
    }

    public ReportPK(String questionID, String studyID) {
        this.questionID = questionID;
        this.studyID = studyID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        hash += (studyID != null ? studyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportPK)) {
            return false;
        }
        ReportPK other = (ReportPK) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        if ((this.studyID == null && other.studyID != null) || (this.studyID != null && !this.studyID.equals(other.studyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.ReportPK[ questionID=" + questionID + ", studyID=" + studyID + " ]";
    }
    
}
