package data;

import java.util.*;
import javax.persistence.*;

import utility.DBUtil;
import business.User;
import business.Tempuser;
import utility.PasswordUtil;

public class UserDB {

    public static boolean validateUser(String email, String password) {
        User user = getUserByEmail(email);
        String salt = null;
        String hashedPassword = null;
        if (user == null) {
            return false;
        } else {
            salt = user.getSalt();
            hashedPassword = PasswordUtil.getSaltHashPassword(salt, password);
            String userPassword = user.getPassword();
            //String userHashedPassword = PasswordUtil.getSaltHashPassword(salt, userPassword);
            return userPassword.equals(hashedPassword);
        }
    }

    public static List<User> getAllUsers() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select u from User u";
        TypedQuery<User> q = em.createQuery(sqlQuery, User.class);
        //TypedQuery<User> namedQuery = em.createNamedQuery("User.findAll", User.class);
        List<User> users;
        try {
            users = q.getResultList();
            if (users == null || users.isEmpty()) {
                users = null;
            }
        } finally {
            em.close();
        }
        return users;
    }
    
    public static Tempuser getTempUserByToken(String token) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select t from Tempuser t where t.token = :token";
        TypedQuery<Tempuser> q = em.createQuery(sqlQuery, Tempuser.class);
        q.setParameter("token", token);
        Tempuser tempuser = null;
        try {
            tempuser = q.getSingleResult();
        } catch (NoResultException e){
          System.err.println(e);  
        } finally {
            em.close();
        }
        return tempuser;
    }

    public static User getUserByEmail(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public static User getUserByToken(String token) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String sqlQuery = "select u from User u where u.token = :token";
        TypedQuery<User> q = em.createQuery(sqlQuery, User.class);
        q.setParameter("token", token);
        User user = null;
        try {
            user = q.getSingleResult();
        } catch (NoResultException e){
          System.err.println(e);  
        } finally {
            em.close();
        }
        return user;
    }

    public static void addUser(User user) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(user);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void removeTempUser(Tempuser user) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.merge(user));
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void addTempUser(Tempuser tempUser) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(tempUser);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateUser(User user) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(user);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
}
