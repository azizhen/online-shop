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

package co.za.hendricks.dao;

import co.za.hendricks.domain.Basket;
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
    
    /**
     * Persist User entity
     * @param user 
     */
    public void createUser(User user){
        
        //TODO Need to figure out how to configure the transactions so that Spring manages this using the @Transaction annotation
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        
        //Encrypt the password using algorithm so that clear text is not stored in the database
        user.setPassword(ApplicationUtility.getInstance().encrypt(user.getPassword()));
        this.sessionFactory.getCurrentSession().save(user);
        tx.commit();
    }
    
    /**
     * Return all Users
     * @return 
     */
    public List<User> findAll(){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        List <User> userList =  this.sessionFactory.getCurrentSession().createQuery("from User").list();
        tx.commit();
        return userList;
    }
    
    /**
     * Return user using username
     * @param username
     * @return 
     */
    public User getUserByUsername(String username){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
                
        User user =  (User)this.sessionFactory.getCurrentSession().createQuery("select u from User u where u.username = :username")
        .setParameter("username", username).uniqueResult();
     
        tx.commit();
        return user;
    }
    
    /**
     * Attach Product to User Basket and merge user
     * @param user
     * @param product
     * @return 
     */
    public List <Product> addProductToUserBasket(User user, Product product){
        
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        
        //Create a basket for the user if this is the first product
        if(user.getBasket() == null){
            Basket basket = new Basket();
            user.setBasket(basket);
        }
        
        user.getBasket().getProducts().add(product);
        this.sessionFactory.getCurrentSession().merge(user);
        tx.commit();
        
        return user.getBasket().getProducts();
    }
 
}
