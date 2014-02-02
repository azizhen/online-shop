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

    /**
     * Create a Book entity and persist to DB
     * @param book
     * @return 
     */
    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public @ResponseBody
    Book createBook(@RequestBody Book book) {
        
        productService.createProduct(book);
        return book;

    }

    /**
     * Create a Game object and persist to DB
     * @param game
     * @return 
     */
    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public @ResponseBody
    Product createGame(@RequestBody Game game) {

        productService.createProduct(game);
        return game;
    
    }

    /**
     * List all Products currently in database
     * @return 
     */
    @RequestMapping("/listProducts")
    public @ResponseBody
    List<Product> getProductList() {

        return productService.getProductList();
    
    }

    /**
     * Retrieve a Product using the product id
     * @param id
     * @return 
     */
    @RequestMapping("/product")
    public @ResponseBody
    Product getProduct(@RequestParam(value = "productID", required = true) long id) {
    
        return productService.getProduct(id);
    
    }
}
