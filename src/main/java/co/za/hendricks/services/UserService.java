/*
 * Copyright (C) 2014 aziz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * UserService implements all User related operations
 * @author aziz
 */
@Service
public class UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    /**
     * Create a User
     * @param user 
     */
    @Transactional
    public void createUser(User user){
        userDAO.createUser(user);
    }

    /**
     * Returns list of all users
     * @return 
     */
    @Transactional
    public List<User> getUserList() {
        return userDAO.findAll();
    }
    
    /**
     * Get User by username
     * @param username
     * @return 
     */
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
