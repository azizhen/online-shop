/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.dao;

import co.za.hendricks.domain.Product;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aziz
 */
@Repository
public class ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    public void createProduct(Product product){
        sessionFactory.getCurrentSession().save(product);
    }
    
    public List<Product> findAll(){
        return sessionFactory.getCurrentSession().createQuery("select * from Product").list();
    }
 
}
