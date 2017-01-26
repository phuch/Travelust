package Model;

import Model.Journal;
import Model.Likes;
import Model.Picture;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Content.class)
public class Content_ { 

    public static volatile CollectionAttribute<Content, Picture> pictureCollection;
    public static volatile SingularAttribute<Content, Journal> journalId;
    public static volatile SingularAttribute<Content, Integer> contentId;
    public static volatile SingularAttribute<Content, String> textDescription;
    public static volatile CollectionAttribute<Content, Likes> likesCollection;
    public static volatile SingularAttribute<Content, String> contentType;

}