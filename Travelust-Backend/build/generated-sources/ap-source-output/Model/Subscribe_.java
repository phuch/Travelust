package Model;

import Model.Journal;
import Model.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Subscribe.class)
public class Subscribe_ { 

    public static volatile SingularAttribute<Subscribe, Journal> journalId;
    public static volatile SingularAttribute<Subscribe, Integer> id;
    public static volatile SingularAttribute<Subscribe, Users> userId;

}