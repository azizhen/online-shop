/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody User createUser(
           @RequestBody User user){
        userService.createUser(user);
        return user;
        
    }    
        
    @RequestMapping("/listUsers")
    public @ResponseBody List<User> getUserList(){
        
        return userService.getUserList();
    }
    
    @RequestMapping("/login")
    public @ResponseBody boolean login(@RequestParam(value="username", required=true) String username, 
                                       @RequestParam(value="password", required=true) String password){
        return userService.userLogin(username, password);
    }
    
    @RequestMapping("/userDetails")
    public @ResponseBody User user(@RequestParam(value="username", required=true) String username){
        return userService.getUser(username);
    }
    
    @RequestMapping("/addProductToBasket")
    public @ResponseBody List<Product> addProductToUserBasket(@RequestParam(value="username", required=true) String username, 
                                       @RequestParam(value="productID", required=true) long productID){
        return basketService.addProductToUserBasket(username, productID);
    }
}
