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

import co.za.hendricks.domain.Basket;
import co.za.hendricks.domain.Book;
import co.za.hendricks.domain.Company;
import co.za.hendricks.domain.Game;
import co.za.hendricks.domain.Person;
import co.za.hendricks.domain.Price;
import co.za.hendricks.domain.Supplier;
import co.za.hendricks.services.ProductService;
import co.za.hendricks.services.UserService;
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
    
    @Autowired
    private UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(LogbackResponseServletFilter.class);
    
    /**
     * Overiding the onApplicationEvent that detects if Spring Context is loaded.
     * Uses a static variable to ensure that this runs only once
     * @param event 
     */
     @Override
     public void onApplicationEvent(ContextRefreshedEvent event) {
            
            /* ContextRefreshedEvent could be triggered more than once, 
               therefore build in a check to prevent this from running twicw
             */
            if(DataLoader.isDataLoaded != true){
             
             logger.info("-------------------------------");
             logger.info("Loading Default Data for application.");
             logger.info("-------------------------------");
             
             logger.info("Loading Books started...");
             createBookHelper("War of the Worlds", "Science Fiction Classic", "1133857396", "Caxton Books", 100.00, 159.00);
             createBookHelper("Life the Universe and Everything", "Science Fiction Classic", "1133857396", "Caxton Books", 120.00, 169.00);
             createBookHelper("The Amazing X-Men", "Comic Book", "1133857396", "Marvel Comics", 40.00, 10.00);
             createBookHelper("Uncanny X-men", "Comic Book", "1133857396", "Caxton Books", 100.00, 159.00);
             logger.info("Loading Books completed...");
             
             logger.info("Loading Games started...");
             createGameHelper("FIFA 14", "Soccer Simulation", "PS3", "EA Sports", 250.00, 550.00);
             createGameHelper("Grand Theft Auto V", "Open World Third Person", "PS3", "Rockstar Games", 299.00, 750.00);
             logger.info("Loading Games completed...");
             
             logger.info("Loading Users started...");
             createPersonUserHelper("aziz", "Aziz", "Hendricks", "password");
             createPersonUserHelper("admin", "admin", "admin", "password");
             createCompanyUserHelper("company", "Big Company", "vat1234", "password");
             logger.info("Loading Users completed...");
             
             
             logger.info("-------------------------------");
             logger.info("Loading Data completed.");
             logger.info("-------------------------------");
             DataLoader.isDataLoaded = true;   
            
              }
        }
        
        /**
         * Utility method to create a Book object
         * @param title
         * @param shortDescription
         * @param isbn
         * @param supplierName
         * @param costPrice
         * @param sellingPrice 
         */
        private void createBookHelper(String title, String shortDescription, String isbn, String supplierName, double costPrice, double sellingPrice){
             
             Book book = new Book();
             book.setTitle(title);
             book.setShortDescription(shortDescription);
             book.setIsbn(isbn);
             
             Supplier supplier = new Supplier();
             supplier.setName(supplierName);
             
             Price price  = new Price();
             price.setCostPrice(costPrice);
             price.setSellingPrice(sellingPrice);
             price.setSupplier(supplier);
             
             book.setPrice(price);
             
             productService.createProduct(book);
        }
        
        /**
         * Utility method to create a Game object
         * @param title
         * @param shortDescription
         * @param format
         * @param supplierName
         * @param costPrice
         * @param sellingPrice 
         */
        private void createGameHelper(String title, String shortDescription, String format, String supplierName, double costPrice, double sellingPrice){
             
             Game game = new Game();
             game.setTitle(title);
             game.setShortDescription(shortDescription);
             game.setFormat(format);
             
             Supplier supplier = new Supplier();
             supplier.setName(supplierName);
             
             Price price  = new Price();
             price.setCostPrice(costPrice);
             price.setSellingPrice(sellingPrice);
             price.setSupplier(supplier);
             
             game.setPrice(price);
             
             productService.createProduct(game);
        }
        
        /**
         * Utility method to create Person users
         * @param username
         * @param firstname
         * @param lastName
         * @param password 
         */
         private void createPersonUserHelper(String username, String firstname, String lastName, String password){
             
             Person person = new Person();
             person.setFirstName(firstname);
             person.setLastName(lastName);
             person.setUsername(username);
             person.setPassword(password);
             
             //Empty Basket
             Basket basket = new Basket();
             person.setBasket(basket);
             
             userService.createUser(person);
        }
         
         /**
          * Utility method to create Company Users
          * @param username
          * @param name
          * @param vatNo
          * @param password 
          */
         private void createCompanyUserHelper(String username, String name, String vatNo, String password){
             
             Company company = new Company();
             company.setName(name);
             company.setVatNo(vatNo);
             company.setUsername(username);
             company.setPassword(password);
             
             //Empty Basket
             Basket basket = new Basket();
             company.setBasket(basket);
             
             userService.createUser(company);
        }
}
