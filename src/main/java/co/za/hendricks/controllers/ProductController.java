/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.controllers;

import co.za.hendricks.domain.Book;
import co.za.hendricks.domain.Game;
import co.za.hendricks.domain.Product;
import co.za.hendricks.services.ProductService;
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
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public @ResponseBody Book createBook(
           @RequestBody Book book){
        productService.createProduct(book);
        return book;
        
    }    
    
    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public @ResponseBody Product createGame(
           @RequestBody Game game){

        productService.createProduct(game);        
        return game;
    }
    
    @RequestMapping("/listProducts")
    public @ResponseBody List<Product> getProductList(){
        
        return productService.getProductList();
    }
    
    @RequestMapping("/product")
    public @ResponseBody Product getProduct(@RequestParam(value="productID", required=true) long id){
        return productService.getProduct(id);
    }
    
}
