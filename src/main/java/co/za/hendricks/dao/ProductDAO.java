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
    
    /**
     * Persist Product entity
     * @param product 
     */
    public void createProduct(Product product){
        
        //TODO Need to figure out how to configure the transactions so that Spring manages this using the @Transaction annotation
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        this.sessionFactory.getCurrentSession().save(product);
        tx.commit();
        }
    
    /**
     * Return all Products
     * @return 
     */
    public List<Product> findAll(){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        List <Product> productList =  this.sessionFactory.getCurrentSession().createQuery("from Product").list();
        tx.commit();
        return productList;
    }
    
    /**
     * Return Product by ID
     * @param productID
     * @return 
     */
    public Product getProduct(long productID){
        Transaction tx  = this.sessionFactory.getCurrentSession().beginTransaction();
        Product product =  (Product)this.sessionFactory.getCurrentSession().get(Product.class, productID);
        tx.commit();
        return product;
    } 
}
