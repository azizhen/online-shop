/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.dao;

import co.za.hendricks.domain.Product;
import co.za.hendricks.domain.User;
import co.za.hendricks.utility.ApplicationUtility;
import com.willvuong.bootstrapper.filter.LogbackResponseServletFilter;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aziz
 */
@Repository
public class UserDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(LogbackResponseServletFilter.class);
    private SessionFactory sessionFactory;
    
    @Autowired
    public UserDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public void createUser(User user){
        
        //TODO Need to figure out how to configure the transactions so that Spring manages this using the @Transaction annotation
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        
        //Encrypt the password using algorithm so that clear text is not stored in the database
        user.setPassword(ApplicationUtility.getInstance().encrypt(user.getPassword()));
        this.sessionFactory.getCurrentSession().save(user);
        tx.commit();
    }
    
    
    public List<User> findAll(){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        List <User> userList =  this.sessionFactory.getCurrentSession().createQuery("from User").list();
        tx.commit();
        return userList;
    }
    
    public User getUserByUsername(String username){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        User user =  (User)this.sessionFactory.getCurrentSession().createQuery("select u from User u where u.username = :username")
        .setParameter("username", username).uniqueResult();
     
        tx.commit();
        return user;
    }
 
}
