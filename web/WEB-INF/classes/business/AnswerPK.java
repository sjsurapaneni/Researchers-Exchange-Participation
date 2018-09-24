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
public class AnswerPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "StudyID")
    private String studyID;
    @Basic(optional = false)
    @Column(name = "QuestionID")
    private String questionID;
    @Basic(optional = false)
    @Column(name = "Useremail")
    private String useremail;

    public AnswerPK() {
    }

    public AnswerPK(String studyID, String questionID, String useremail) {
        this.studyID = studyID;
        this.questionID = questionID;
        this.useremail = useremail;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyID != null ? studyID.hashCode() : 0);
        hash += (questionID != null ? questionID.hashCode() : 0);
        hash += (useremail != null ? useremail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerPK)) {
            return false;
        }
        AnswerPK other = (AnswerPK) object;
        if ((this.studyID == null && other.studyID != null) || (this.studyID != null && !this.studyID.equals(other.studyID))) {
            return false;
        }
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        if ((this.useremail == null && other.useremail != null) || (this.useremail != null && !this.useremail.equals(other.useremail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.AnswerPK[ studyID=" + studyID + ", questionID=" + questionID + ", useremail=" + useremail + " ]";
    }
    
}
