/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.services;

import co.za.hendricks.dao.ProductDAO;
import co.za.hendricks.dao.UserDAO;
import co.za.hendricks.domain.Product;
import co.za.hendricks.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aziz
 */
@Service
public class BasketService {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    @Transactional
    public List<Product> addProductToUserBasket(String username, long productID) {
        
        //Retrieve the user
        User user = userDAO.getUserByUsername(username);
        
        //Retrieve the product
        Product product  = productDAO.getProduct(productID);
        
        return userDAO.addProductToUserBasket(user, product);
    }
}
