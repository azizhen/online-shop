/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.services;

import co.za.hendricks.dao.ProductDAO;
import co.za.hendricks.domain.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aziz
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductDAO productDAO;
    
    public void createProduct(Product product){
        productDAO.createProduct(product);
    }

    public List<Product> getProductList() {
        return productDAO.findAll();
    }
}
