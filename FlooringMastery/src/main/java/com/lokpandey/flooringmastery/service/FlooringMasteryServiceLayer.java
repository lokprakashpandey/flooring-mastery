/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Interface for service layer
 */
package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author lokpandey
 */
public interface FlooringMasteryServiceLayer {
    
    /**
     * Reads all the orders from the dateString given.
     *
     * @param String dateString the date of the order to read orders from
     * @return List<Order> if file exists otherwise 
     * @throws FileNotFoundException when file does not exist
     * @throws InvalidDateException when date is not in proper format
    */
    
    List<Order> readOrdersFromFile(String dateString) 
            throws FileNotFoundException, InvalidDateException;
}
