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

import co.za.hendricks.dao.ProductDAO;
import co.za.hendricks.dao.UserDAO;
import co.za.hendricks.domain.Product;
import co.za.hendricks.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BasketService implements functionality that requires both UserDAO and ProductDAO.
 * @author aziz
 */
@Service
public class BasketService {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    /**
     * Attach Product to User Basket
     * @param username
     * @param productID
     * @return 
     */
    @Transactional
    public List<Product> addProductToUserBasket(String username, long productID) {
        
        //Retrieve the user
        User user = userDAO.getUserByUsername(username);
        
        //Retrieve the product
        Product product  = productDAO.getProduct(productID);
        
        return userDAO.addProductToUserBasket(user, product);
    }
}
