/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: FlooringMastery dao interface
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author lokpandey
 */
public interface FlooringMasteryDao {
    
    /**
     * Selects all the orders from the file name provided
     *
     * @param String fileName to read orders from
     * @return List<Order> if orders file exists otherwise null
     * @throws FlooringMasteryPersistenceException when file cannot be read
    */
    public List<Order> selectAllFromOrders(String fileName) 
            throws FileNotFoundException;
    
}
