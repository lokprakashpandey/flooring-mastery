/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 11, 2022
 * purpose: Interface for products file
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Product;
import java.io.FileNotFoundException;
import java.util.List;


public interface FlooringMasteryProductsDao {
    
    /**
     * Selects all the products from the products text file
     *
     * @param void 
     * @return List<Product> if products file exists otherwise null
     * @throws FileNotFoundException when file cannot be read
    */
    
    List<Product> selectAllFromProducts() throws FileNotFoundException;

}
