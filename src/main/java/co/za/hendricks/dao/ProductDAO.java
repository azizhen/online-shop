/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.za.hendricks.dao;

import co.za.hendricks.domain.Product;
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
public class ProductDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(LogbackResponseServletFilter.class);

    
    private SessionFactory sessionFactory;
    
    @Autowired
    public ProductDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    public void createProduct(Product product){
        
        //TODO Need to figure out how to configure the transactions so that Spring manages this using the @Transaction annotation
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        this.sessionFactory.getCurrentSession().save(product);
        tx.commit();
        }
    
    
    public List<Product> findAll(){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        List <Product> productList =  this.sessionFactory.getCurrentSession().createQuery("from Product").list();
        tx.commit();
        return productList;
    }
    
    public Product getProduct(long productID){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        Product product =  (Product)this.sessionFactory.getCurrentSession().get(Product.class, productID);
        tx.commit();
        return product;
    }
 
}
