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

package co.za.hendricks.controllers;

import co.za.hendricks.domain.Product;
import co.za.hendricks.domain.User;
import co.za.hendricks.services.BasketService;
import co.za.hendricks.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aziz
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private BasketService basketService;
  
    /**
     * Create a User entity and persist to DB
     * @param user
     * @return 
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody User createUser(
           @RequestBody User user){
        userService.createUser(user);
        return user;
        
    }    
        
    /**
     * List all Users currently in the database
     * @return 
     */
    @RequestMapping("/listUsers")
    public @ResponseBody List<User> getUserList(){
        
        return userService.getUserList();
    }
    
    /**
     * Authenticate user using username and password
     * @param username
     * @param password
     * @return 
     */
    @RequestMapping("/login")
    public @ResponseBody boolean login(@RequestParam(value="username", required=true) String username, 
                                       @RequestParam(value="password", required=true) String password){
        return userService.userLogin(username, password);
    }
    
    /**
     * Retrieve User details using the username
     * @param username
     * @return 
     */
    @RequestMapping("/userDetails")
    public @ResponseBody User user(@RequestParam(value="username", required=true) String username){
        return userService.getUser(username);
    }
    
    /**
     * Attach a Product to User's Basket
     * @param username
     * @param productID
     * @return 
     */
    @RequestMapping("/addProductToBasket")
    public @ResponseBody List<Product> addProductToUserBasket(@RequestParam(value="username", required=true) String username, 
                                       @RequestParam(value="productID", required=true) long productID){
        return basketService.addProductToUserBasket(username, productID);
    }
}
