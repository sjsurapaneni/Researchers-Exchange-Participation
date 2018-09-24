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
@Table(name = "Answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByStudyID", query = "SELECT a FROM Answer a WHERE a.answerPK.studyID = :studyID"),
    @NamedQuery(name = "Answer.findByQuestionID", query = "SELECT a FROM Answer a WHERE a.answerPK.questionID = :questionID"),
    @NamedQuery(name = "Answer.findByUseremail", query = "SELECT a FROM Answer a WHERE a.answerPK.useremail = :useremail"),
    @NamedQuery(name = "Answer.findByChoice", query = "SELECT a FROM Answer a WHERE a.choice = :choice"),
    @NamedQuery(name = "Answer.findByDateSubmitted", query = "SELECT a FROM Answer a WHERE a.dateSubmitted = :dateSubmitted")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnswerPK answerPK;
    @Column(name = "Choice")
    private String choice;
    @Column(name = "DateSubmitted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;
    @JoinColumn(name = "Useremail", referencedColumnName = "Useremail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Answer() {
    }

    public Answer(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public Answer(String studyID, String questionID, String useremail) {
        this.answerPK = new AnswerPK(studyID, questionID, useremail);
    }

    public AnswerPK getAnswerPK() {
        return answerPK;
    }

    public void setAnswerPK(AnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerPK != null ? answerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerPK == null && other.answerPK != null) || (this.answerPK != null && !this.answerPK.equals(other.answerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.Answer[ answerPK=" + answerPK + " ]";
    }
    
}
