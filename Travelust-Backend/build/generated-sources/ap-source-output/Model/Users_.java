package Model;

import Model.Journal;
import Model.Likes;
import Model.Subscribe;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> fname;
    public static volatile SingularAttribute<Users, String> userHash;
    public static volatile SingularAttribute<Users, String> profilepicturePath;
    public static volatile SingularAttribute<Users, Boolean> userStatus;
    public static volatile SingularAttribute<Users, String> salt;
    public static volatile SingularAttribute<Users, Date> validStartTime;
    public static volatile SingularAttribute<Users, Date> dateOfBirth;
    public static volatile CollectionAttribute<Users, Journal> journalCollection;
    public static volatile CollectionAttribute<Users, Subscribe> subscribeCollection;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, String> token;
    public static volatile SingularAttribute<Users, Boolean> userActive;
    public static volatile SingularAttribute<Users, String> lname;
    public static volatile SingularAttribute<Users, Date> validEndTime;
    public static volatile CollectionAttribute<Users, Likes> likesCollection;
    public static volatile SingularAttribute<Users, String> email;

}