package Model;

import Model.Content;
import Model.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Likes.class)
public class Likes_ { 

    public static volatile SingularAttribute<Likes, Content> contentId;
    public static volatile SingularAttribute<Likes, Integer> id;
    public static volatile SingularAttribute<Likes, Users> userId;

}