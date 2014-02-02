/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.services;

import co.za.hendricks.dao.UserDAO;
import co.za.hendricks.domain.User;
import co.za.hendricks.utility.ApplicationUtility;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aziz
 */
@Service
public class UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    /**
     * Creates user entity
     * @param user 
     */
    @Transactional
    public void createUser(User user){
        userDAO.createUser(user);
    }

    /**
     * Returns list of all users in the database
     * @return 
     */
    @Transactional
    public List<User> getUserList() {
        return userDAO.findAll();
    }
    
    @Transactional
    public User getUser(String username) {
        return userDAO.getUserByUsername(username);
    }
    
    /**
     * Encrypts the users password and then compares the hashed password to the
     * password stored in the database
     * @param username
     * @param password
     * @return 
     */
    @Transactional
    public boolean userLogin(String username, String password) {
        User user = this.getUser(username);
        
        //If user does not exist, then fail authentication
        if(user == null){
            return false;
        }
        
        //Use the one way encryption to ensure that the user's password matches the encrypted version
        if(user.getPassword().equalsIgnoreCase(ApplicationUtility.getInstance().encrypt(password))){
            return true;
        }else{
            return false;
        }
        
    }
}
