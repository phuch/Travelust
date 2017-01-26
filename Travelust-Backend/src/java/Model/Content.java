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
@Table(name = "Content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c")
    , @NamedQuery(name = "Content.findByContentId", query = "SELECT c FROM Content c WHERE c.contentId = :contentId")
    , @NamedQuery(name = "Content.findByTextDescription", query = "SELECT c FROM Content c WHERE c.textDescription = :textDescription")
    , @NamedQuery(name = "Content.findByContentType", query = "SELECT c FROM Content c WHERE c.contentType = :contentType")})
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "content_id")
    private Integer contentId;
    @Column(name = "text_description")
    private String textDescription;
    @Column(name = "content_type")
    private String contentType;
    @JoinColumn(name = "journal_id", referencedColumnName = "journal_id")
    @ManyToOne
    private Journal journalId;
    @OneToMany(mappedBy = "contentId")
    private Collection<Picture> pictureCollection;
    @OneToMany(mappedBy = "contentId")
    private Collection<Likes> likesCollection;

    public Content() {
    }

    public Content(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Journal getJournalId() {
        return journalId;
    }

    public void setJournalId(Journal journalId) {
        this.journalId = journalId;
    }

    @XmlTransient
    public Collection<Picture> getPictureCollection() {
        return pictureCollection;
    }

    public void setPictureCollection(Collection<Picture> pictureCollection) {
        this.pictureCollection = pictureCollection;
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
        hash += (contentId != null ? contentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.contentId == null && other.contentId != null) || (this.contentId != null && !this.contentId.equals(other.contentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Content[ contentId=" + contentId + " ]";
    }
    
}
