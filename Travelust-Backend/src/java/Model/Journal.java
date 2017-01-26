/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author ADMIN
 */
@Entity
@Table(name = "Journal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journal.findAll", query = "SELECT j FROM Journal j")
    , @NamedQuery(name = "Journal.findByJournalId", query = "SELECT j FROM Journal j WHERE j.journalId = :journalId")
    , @NamedQuery(name = "Journal.findByTitle", query = "SELECT j FROM Journal j WHERE j.title = :title")
    , @NamedQuery(name = "Journal.findByLocation", query = "SELECT j FROM Journal j WHERE j.location = :location")
    , @NamedQuery(name = "Journal.findByDescription", query = "SELECT j FROM Journal j WHERE j.description = :description")
    , @NamedQuery(name = "Journal.findByCoverpicPath", query = "SELECT j FROM Journal j WHERE j.coverpicPath = :coverpicPath")})
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "journal_id")
    private Integer journalId;
    @Column(name = "title")
    private String title;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;
    @Column(name = "coverpic_path")
    private String coverpicPath;
    @OneToMany(mappedBy = "journalId")
    private Collection<Content> contentCollection;
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users ownerId;
    @OneToMany(mappedBy = "journalId")
    private Collection<Subscribe> subscribeCollection;

    public Journal() {
    }

    public Journal(Integer journalId) {
        this.journalId = journalId;
    }
    
    public Journal(String title, String location, String description, Users user, String coverpic) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.ownerId = user;
        this.coverpicPath = coverpic;
    }

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverpicPath() {
        return coverpicPath;
    }

    public void setCoverpicPath(String coverpicPath) {
        this.coverpicPath = coverpicPath;
    }

    @XmlTransient
    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }

    @XmlTransient
    public Collection<Subscribe> getSubscribeCollection() {
        return subscribeCollection;
    }

    public void setSubscribeCollection(Collection<Subscribe> subscribeCollection) {
        this.subscribeCollection = subscribeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (journalId != null ? journalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Journal)) {
            return false;
        }
        Journal other = (Journal) object;
        if ((this.journalId == null && other.journalId != null) || (this.journalId != null && !this.journalId.equals(other.journalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Journal[ journalId=" + journalId + " ]";
    }
    
}
