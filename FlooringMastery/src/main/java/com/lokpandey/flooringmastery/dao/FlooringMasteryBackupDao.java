/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Interface for orders backup
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.util.List;


public interface FlooringMasteryBackupDao {
    
    /**
     * Returns a sorted list of all the orders and exports it to a file
     *
     * @param void 
     * @return List<Order> of all Orders files
     * @throws FlooringMasteryPersistenceException when orders file cannot be read 
     * or exports file cannot be created 
    */
    List<Order> exportAllOrders() throws FlooringMasteryPersistenceException;
}
