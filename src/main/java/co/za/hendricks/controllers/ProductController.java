/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.controllers;

import co.za.hendricks.dao.ProductDAO;
import co.za.hendricks.domain.Product;
import co.za.hendricks.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/createProduct")
    public @ResponseBody Product createProduct(
           @RequestParam(value="title", required=false, defaultValue="default title")String title){
        Product testProduct = new Product();
        testProduct.setTitle(title);
        productService.createProduct(testProduct);
        
        return testProduct;
    }
    
    @RequestMapping("/listProducts")
    public @ResponseBody List<Product> getProductList(){
        
        return productService.getProductList();
    }
    
}
