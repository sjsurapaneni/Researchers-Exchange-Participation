/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "Question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQuestionID", query = "SELECT q FROM Question q WHERE q.questionID = :questionID"),
    @NamedQuery(name = "Question.findByQuestion", query = "SELECT q FROM Question q WHERE q.question = :question"),
    @NamedQuery(name = "Question.findByAnswerType", query = "SELECT q FROM Question q WHERE q.answerType = :answerType"),
    @NamedQuery(name = "Question.findByOption1", query = "SELECT q FROM Question q WHERE q.option1 = :option1"),
    @NamedQuery(name = "Question.findByOption2", query = "SELECT q FROM Question q WHERE q.option2 = :option2"),
    @NamedQuery(name = "Question.findByOption3", query = "SELECT q FROM Question q WHERE q.option3 = :option3"),
    @NamedQuery(name = "Question.findByOption4", query = "SELECT q FROM Question q WHERE q.option4 = :option4"),
    @NamedQuery(name = "Question.findByOption5", query = "SELECT q FROM Question q WHERE q.option5 = :option5")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "QuestionID")
    private String questionID;
    @Column(name = "Question")
    private String question;
    @Column(name = "AnswerType")
    private String answerType;
    @Column(name = "Option1")
    private String option1;
    @Column(name = "Option2")
    private String option2;
    @Column(name = "Option3")
    private String option3;
    @Column(name = "Option4")
    private String option4;
    @Column(name = "Option5")
    private String option5;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Collection<Report> reportCollection;
    @JoinColumn(name = "StudyID", referencedColumnName = "StudyID")
    @ManyToOne
    private Study studyID;

    public Question() {
    }

    public Question(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    public Study getStudyID() {
        return studyID;
    }

    public void setStudyID(Study studyID) {
        this.studyID = studyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.Question[ questionID=" + questionID + " ]";
    }
    
}
