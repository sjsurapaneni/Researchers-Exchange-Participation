/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinayak
 */
@Entity
@Table(name = "tempuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tempuser.findAll", query = "SELECT t FROM Tempuser t"),
    @NamedQuery(name = "Tempuser.findByUsername", query = "SELECT t FROM Tempuser t WHERE t.username = :username"),
    @NamedQuery(name = "Tempuser.findByEmail", query = "SELECT t FROM Tempuser t WHERE t.email = :email"),
    @NamedQuery(name = "Tempuser.findByPassword", query = "SELECT t FROM Tempuser t WHERE t.password = :password"),
    @NamedQuery(name = "Tempuser.findByIssueDate", query = "SELECT t FROM Tempuser t WHERE t.issueDate = :issueDate"),
    @NamedQuery(name = "Tempuser.findByToken", query = "SELECT t FROM Tempuser t WHERE t.token = :token")})
public class Tempuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Username")
    private String username;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "IssueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Column(name = "Recommender_email")
    private String recommenderEmail;
    @Id
    @Basic(optional = false)
    @Column(name = "Token")
    private String token;

    public Tempuser() {
    }

    public Tempuser(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecommenderEmail() {
        return recommenderEmail;
    }

    public void setRecommenderEmail(String recommenderEmail) {
        this.recommenderEmail = recommenderEmail;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tempuser)) {
            return false;
        }
        Tempuser other = (Tempuser) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.Tempuser[ token=" + token + " ]";
    }
    
}
