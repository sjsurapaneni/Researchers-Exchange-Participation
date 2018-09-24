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
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUseremail", query = "SELECT u FROM User u WHERE u.useremail = :useremail"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type"),
    @NamedQuery(name = "User.findByStudies", query = "SELECT u FROM User u WHERE u.studies = :studies"),
    @NamedQuery(name = "User.findByParticipation", query = "SELECT u FROM User u WHERE u.participation = :participation"),
    @NamedQuery(name = "User.findByCoins", query = "SELECT u FROM User u WHERE u.coins = :coins")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Useremail")
    private String useremail;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Type")
    private String type;
    @Column(name = "Studies")
    private Integer studies;
    @Column(name = "Participation")
    private Integer participation;
    @Column(name = "Coins")
    private Integer coins;
    @Column(name= "Token")
    private String token;
    @Column(name= "Salt")
    private String salt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Answer> answerCollection;
    @OneToMany(mappedBy = "useremail")
    private Collection<Study> studyCollection;

    public User() {
    }

    public User(String useremail) {
        this.useremail = useremail;
    }

    public User(String useremail, String username) {
        this.useremail = useremail;
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStudies() {
        return studies;
    }

    public void setStudies(Integer studies) {
        this.studies = studies;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public Integer getParticipation() {
        return participation;
    }

    public void setParticipation(Integer participation) {
        this.participation = participation;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @XmlTransient
    public Collection<Study> getStudyCollection() {
        return studyCollection;
    }

    public void setStudyCollection(Collection<Study> studyCollection) {
        this.studyCollection = studyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useremail != null ? useremail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.useremail == null && other.useremail != null) || (this.useremail != null && !this.useremail.equals(other.useremail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.User[ useremail=" + useremail + " ]";
    }
    
}
