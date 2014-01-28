/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.controllers;

import co.za.hendricks.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aziz
 */
@Controller
public class ProductController {
    
    @RequestMapping("/product")
    public @ResponseBody Product getProductDetails(
           @RequestParam(value="id", required=false, defaultValue="1")int id){
        Product testProduct = new Product();
        testProduct.setTitle("Book 1");
        return testProduct;
    }
    
}
