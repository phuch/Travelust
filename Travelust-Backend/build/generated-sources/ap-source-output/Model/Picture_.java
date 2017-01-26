package Model;

import Model.Content;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-13T04:24:06")
@StaticMetamodel(Picture.class)
public class Picture_ { 

    public static volatile SingularAttribute<Picture, Integer> pictureId;
    public static volatile SingularAttribute<Picture, String> filepath;
    public static volatile SingularAttribute<Picture, Content> contentId;

}