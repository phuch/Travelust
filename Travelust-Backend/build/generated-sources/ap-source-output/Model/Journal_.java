package Model;

import Model.Content;
import Model.Subscribe;
import Model.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Journal.class)
public class Journal_ { 

    public static volatile CollectionAttribute<Journal, Content> contentCollection;
    public static volatile SingularAttribute<Journal, String> coverpicPath;
    public static volatile SingularAttribute<Journal, Integer> journalId;
    public static volatile SingularAttribute<Journal, String> description;
    public static volatile SingularAttribute<Journal, String> location;
    public static volatile CollectionAttribute<Journal, Subscribe> subscribeCollection;
    public static volatile SingularAttribute<Journal, String> title;
    public static volatile SingularAttribute<Journal, Users> ownerId;

}