/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.EmailSessionBean;
import Controller.SessionBean;
import Model.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * REST Web Service
 *
 * @author ADMIN
 */
@Path("service")
public class RestfulService {
    @EJB
    SessionBean bean;
    
    @EJB
    EmailSessionBean emailBean;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestfulService
     */
    public RestfulService() {
    }

    @Path("verify")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response verifyAccount(@QueryParam("email") String email) {
        if (bean.updateActiveState(email)){
            try {
                return Response.status(Response.Status.FOUND).location(new URI("http://10.114.32.23:8080/login.html")).build();
            } catch (URISyntaxException ex) {
                Logger.getLogger(RestfulService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Response.Status.REQUEST_TIMEOUT).build();
    }

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(@FormParam("email") String email, @FormParam("password") String password, @FormParam("rememberPass") String remember){
        String token = bean.toAuthenticate(email, password);
        if (token != null){
            try {
                if (remember != null){
                    NewCookie cookie = new NewCookie("token", token, "/", "", "comment", 60 * 60 * 24 * 365 * 10, false);
                    return Response.status(Response.Status.FOUND).cookie(cookie).location(new URI("http://10.114.32.23:8080/home.html")).build();
                }else{
                    NewCookie cookie = new NewCookie("token", token, "/", "", "comment", -1, false);
                    return Response.status(Response.Status.FOUND).cookie(cookie).location(new URI("http://10.114.32.23:8080/home.html")).build();
                }
            } catch (URISyntaxException ex) {
                Logger.getLogger(RestfulService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.ok("login failed").build();
    }
    
    @Path("signup")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response signUp (@FormParam("fname") String fname,
                            @FormParam("lname") String lname,
                            @FormParam("bday") String bday,
                            @FormParam("email") String email,
                            @FormParam("pword") String pword) {
//        return Response.status(200)
//			.entity("addUser is called, name : " + lname + ", age : " + bean.isExist(email) + email)
//			.build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = sdf.parse(bday);
            if (!bean.isExist(email)){
               bean.insert(new Users(fname, lname, dob, email, pword, new Date(), Boolean.TRUE));
               emailBean.sendMail(fname + lname, email, "Verify Account");
               return Response.status(Response.Status.FOUND).location(new URI("http://10.114.32.23:8080/login.html")).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(RestfulService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @Path("verifyToken")
    @GET
    public Response authenticateWithToken(@CookieParam("token") String token){
        if (token == null)
            return Response.ok("no token").build();
        
        if (bean.toTokenAuthenticate(token)){
            return Response.ok("http://10.114.32.23:8080/home.html").build();
        }
        return Response.ok("no token").build();
    }
    
    @Path("/upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response uploadFile(
            @CookieParam("token") String token,
            @FormDataParam("title") String title,
            @FormDataParam("location") String location,
            @FormDataParam("description") String description,
            @FormDataParam("fileUpload") InputStream uploadInputStream,
            @FormDataParam("fileUpload") FormDataContentDisposition fileDetail) {

        
        String uploadLocation = "/home/glassfish/glassfish4/glassfish/domains/domain1/docroot/img/img-stream/" + fileDetail.getFileName();

        try {
            // save it
            if(writeToFile(uploadInputStream, uploadLocation)){
                if (appendImgJson(title, location, description, fileDetail.getFileName()) && bean.insertJournal(token, title, location, description, uploadLocation))
                    return Response.status(Response.Status.FOUND).location(new URI("http://10.114.32.23:8080/home.html")).build();
            }else{
                String output = "Upload fail to : " + uploadLocation;
                return Response.status(200).entity(output).build();
            }
        } catch (Exception e) {
            Logger.getLogger(RestfulService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // save uploaded file to new location
    private boolean writeToFile(InputStream uploadInputStream, String uploadLocation) throws IOException{
        try {
            OutputStream out = new FileOutputStream(new File(uploadLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = uploadInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean appendImgJson(String title, String location, String description, String fileName){
        
        JSONParser parser = new JSONParser();
	try {
            Object obj = parser.parse(new FileReader("/home/glassfish/glassfish4/glassfish/domains/domain1/docroot/images.json"));

            JSONArray jsonArr = (JSONArray) obj;
            
            JSONObject img = new JSONObject();
            img.put("mediaTitle", title);
            img.put("mediaUrl", fileName);
            img.put("mediaLocation", location);
            img.put("mediaDescription", description);
            
            jsonArr.add(img);

            FileWriter file = new FileWriter("/home/glassfish/glassfish4/glassfish/domains/domain1/docroot/images.json");
            file.write(jsonArr.toJSONString());
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * PUT method for updating or creating an instance of RestfulService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}