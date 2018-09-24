/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "Study")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Study.findAll", query = "SELECT s FROM Study s"),
    @NamedQuery(name = "Study.findByStudyID", query = "SELECT s FROM Study s WHERE s.studyID = :studyID"),
    @NamedQuery(name = "Study.findByStudyName", query = "SELECT s FROM Study s WHERE s.studyName = :studyName"),
    @NamedQuery(name = "Study.findByDescription", query = "SELECT s FROM Study s WHERE s.description = :description"),
    @NamedQuery(name = "Study.findByDateCreated", query = "SELECT s FROM Study s WHERE s.dateCreated = :dateCreated"),
    @NamedQuery(name = "Study.findByImageURL", query = "SELECT s FROM Study s WHERE s.imageURL = :imageURL"),
    @NamedQuery(name = "Study.findByReqParticipants", query = "SELECT s FROM Study s WHERE s.reqParticipants = :reqParticipants"),
    @NamedQuery(name = "Study.findByActParticipants", query = "SELECT s FROM Study s WHERE s.actParticipants = :actParticipants"),
    @NamedQuery(name = "Study.findBySStatus", query = "SELECT s FROM Study s WHERE s.sStatus = :sStatus")})
public class Study implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "StudyID")
    private String studyID;
    @Column(name = "StudyName")
    private String studyName;
    @Column(name = "Description")
    private String description;
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "ImageURL")
    private String imageURL;
    @Column(name = "ReqParticipants")
    private Integer reqParticipants;
    @Column(name = "ActParticipants")
    private Integer actParticipants;
    @Column(name = "SStatus")
    private String sStatus;
    @JoinColumn(name = "Useremail", referencedColumnName = "Useremail")
    @ManyToOne
    private User useremail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "study")
    private Collection<Report> reportCollection;
    @OneToMany(mappedBy = "studyID")
    private Collection<Question> questionCollection;

    public Study() {
    }

    public Study(String studyID) {
        this.studyID = studyID;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getReqParticipants() {
        return reqParticipants;
    }

    public void setReqParticipants(Integer reqParticipants) {
        this.reqParticipants = reqParticipants;
    }

    public Integer getActParticipants() {
        return actParticipants;
    }

    public void setActParticipants(Integer actParticipants) {
        this.actParticipants = actParticipants;
    }

    public String getSStatus() {
        return sStatus;
    }

    public void setSStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public User getUseremail() {
        return useremail;
    }

    public void setUseremail(User useremail) {
        this.useremail = useremail;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyID != null ? studyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Study)) {
            return false;
        }
        Study other = (Study) object;
        if ((this.studyID == null && other.studyID != null) || (this.studyID != null && !this.studyID.equals(other.studyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.Study[ studyID=" + studyID + " ]";
    }
    
}
