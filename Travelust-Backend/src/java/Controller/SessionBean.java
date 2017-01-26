/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Journal;
import Model.Users;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ADMIN
 */
@Stateless
public class SessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    public void insert(Users u){
        //em.getTransaction().begin();
        em.persist(u);
       // em.getTransaction().commit();
    }
    
    public String toAuthenticate(String email, String password){
        Query checkUser = em.createNamedQuery("Users.toAuthenticate");
        checkUser.setParameter("email", email);
        checkUser.setParameter("password", password);
        List<Users> users = checkUser.getResultList();
        if (users.isEmpty()){
            Logger.getLogger(SessionBean.class.getName()).log(Level.WARNING, "Account does not exist");
            return null;
        }else if(users.size() > 1){
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, "Duplicate entry in database");
            return null;
        }else{
            String token = users.get(0).getUserId().toString() + "a" + generateToken();
            users.get(0).setToken(token);
            update(users.get(0));
            return token;
        }
    }
    
    public boolean toTokenAuthenticate(String token){
        Users user = em.find(Users.class, Integer.parseInt(token.substring(0, token.indexOf("a"))));
        return user.getToken().equals(token);
    }
    
    public boolean isExist(String email){
        Query checkEmail = em.createNamedQuery("Users.findByEmail");
        checkEmail.setParameter("email", email);
        List<Users> users = checkEmail.getResultList();
          
        return users.size() != 0;
    }
    
    public boolean updateActiveState(String email){
        Query checkEmail = em.createNamedQuery("Users.findByEmail");
        checkEmail.setParameter("email", email);
        List<Users> users = checkEmail.getResultList();
        if (users.isEmpty()){
            Logger.getLogger(SessionBean.class.getName()).log(Level.WARNING, "Account does not exist");
            return false;
        }else if(users.size() > 1){
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, "Duplicate entry in database");
            return false;
        }else{
            users.get(0).setUserActive(Boolean.TRUE);
            update(users.get(0));
            return true;
        }
    }
    
    public void update(Users u){
        em.merge(u);
    }
    
    public String generateToken() {
        SecureRandom SECURE_RANDOM = new SecureRandom();
        return new BigInteger(130, SECURE_RANDOM).toString(32);
    }
    
    public boolean insertJournal(String token, String title, String location, String description, String coverpic){
        try{
            int userId = Integer.parseInt(token.substring(0, token.indexOf("a")));
            Users user = em.find(Users.class, userId);
            Journal journal = new Journal(title, location, description, user, coverpic);
            em.persist(journal);
            return true;
        }catch(Exception e){
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, "Duplicate entry in database"); 
        }
        return false;
    }
}
