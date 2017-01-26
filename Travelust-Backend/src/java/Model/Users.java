/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author ADMIN
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByFname", query = "SELECT u FROM Users u WHERE u.fname = :fname")
    , @NamedQuery(name = "Users.findByLname", query = "SELECT u FROM Users u WHERE u.lname = :lname")
    , @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByValidStartTime", query = "SELECT u FROM Users u WHERE u.validStartTime = :validStartTime")
    , @NamedQuery(name = "Users.findByValidEndTime", query = "SELECT u FROM Users u WHERE u.validEndTime = :validEndTime")
    , @NamedQuery(name = "Users.findByUserStatus", query = "SELECT u FROM Users u WHERE u.userStatus = :userStatus")
    , @NamedQuery(name = "Users.findByUserActive", query = "SELECT u FROM Users u WHERE u.userActive = :userActive")
    , @NamedQuery(name = "Users.findBySalt", query = "SELECT u FROM Users u WHERE u.salt = :salt")
    , @NamedQuery(name = "Users.findByUserHash", query = "SELECT u FROM Users u WHERE u.userHash = :userHash")
    , @NamedQuery(name = "Users.findByProfilepicturePath", query = "SELECT u FROM Users u WHERE u.profilepicturePath = :profilepicturePath")
    , @NamedQuery(name = "Users.findByToken", query = "SELECT u FROM Users u WHERE u.token = :token")
    , @NamedQuery(name = "Users.toAuthenticate", query = "SELECT u FROM Users u WHERE u.email = :email AND u.userHash = :password AND u.userActive = 1")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "validStartTime")
    @Temporal(TemporalType.DATE)
    private Date validStartTime;
    @Column(name = "validEndTime")
    @Temporal(TemporalType.DATE)
    private Date validEndTime;
    @Column(name = "user_status")
    private Boolean userStatus;
    @Column(name = "user_active")
    private Boolean userActive;
    @Column(name = "salt")
    private String salt;
    @Column(name = "user_hash")
    private String userHash;
    @Column(name = "profilepicture_path")
    private String profilepicturePath;
    @Column(name = "token")
    private String token;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Journal> journalCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Subscribe> subscribeCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Likes> likesCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String fname, String lname, String email) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    public Users(String fname, String lname, Date dob, String email, String pw, Date validStart, boolean status){
        this.fname = fname;
        this.lname = lname;
        this.dateOfBirth = dob;
        this.email = email;
        this.userHash = pw;
        this.userStatus = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getUserActive() {
        return userActive;
    }

    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public String getProfilepicturePath() {
        return profilepicturePath;
    }

    public void setProfilepicturePath(String profilepicturePath) {
        this.profilepicturePath = profilepicturePath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlTransient
    public Collection<Journal> getJournalCollection() {
        return journalCollection;
    }

    public void setJournalCollection(Collection<Journal> journalCollection) {
        this.journalCollection = journalCollection;
    }

    @XmlTransient
    public Collection<Subscribe> getSubscribeCollection() {
        return subscribeCollection;
    }

    public void setSubscribeCollection(Collection<Subscribe> subscribeCollection) {
        this.subscribeCollection = subscribeCollection;
    }

    @XmlTransient
    public Collection<Likes> getLikesCollection() {
        return likesCollection;
    }

    public void setLikesCollection(Collection<Likes> likesCollection) {
        this.likesCollection = likesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Users[ userId=" + userId + " ]";
    }
    
}
