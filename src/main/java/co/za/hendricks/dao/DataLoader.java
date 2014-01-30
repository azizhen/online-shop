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

import co.za.hendricks.domain.Book;
import co.za.hendricks.domain.Price;
import co.za.hendricks.domain.Supplier;
import co.za.hendricks.services.ProductService;
import com.willvuong.bootstrapper.filter.LogbackResponseServletFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 * @author aziz
 */
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{

public static boolean isDataLoaded = false;
    @Autowired
    private ProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(LogbackResponseServletFilter.class);
    @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            
            if(DataLoader.isDataLoaded != true){
                
            
            logger.info("-------------------------------");
            logger.info("Loading Default Data for application.");
            logger.info("-------------------------------");
             
            
             logger.info("Loading Books started...");
             Book book = new Book();
             book.setIsbn("ISBN1234");
             book.setTitle("War of the Worlds");
             book.setShortDescription("Sci Fi");
             Supplier supplier = new Supplier();
             supplier.setName("Caxton Books");
             
             Price price  = new Price();
             price.setCostPrice(100.00);
             price.setSellingPrice(200.00);
             price.setSupplier(supplier);
             
             
             book.setPrice(price);
             productService.createProduct(book);
             
             logger.info("Loading Books completed...");
            logger.info("-------------------------------");
            logger.info("Loading Data completed.");
            logger.info("-------------------------------");
             DataLoader.isDataLoaded = true;   
            
              }
        }
}
