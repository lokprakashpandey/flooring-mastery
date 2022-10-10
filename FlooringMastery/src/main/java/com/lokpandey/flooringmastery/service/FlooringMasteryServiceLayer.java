/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Interface for service layer
 */
package com.lokpandey.flooringmastery.service;

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
     * @throws InvalidDataException when date is not in proper format
    */
    List<Order> readOrdersFromFile(String dateString) 
            throws FileNotFoundException, InvalidDataException;
    
    
    /**
     * Checks if the dateString given belongs to a future date.
     *
     * @param String dateString the date for the order
     * @return Boolean true if the dateString is a future date 
     * or false if not
     * @throws InvalidDataException when date is not in proper format
    */
    boolean isFutureDate(String dateString) throws InvalidDataException;
    
    /**
     * Checks if the customer name given satisfies a pattern for name.
     * The pattern is it may not be blank, allowed to contain [a-z][0-9] 
     * as well as periods and comma characters. For e.g., "Acme, Inc." is a valid name.
     * 
     * @param String customerName is the name of the customer
     * @return Boolean true if the customer name rule is satisfied 
     * or false if not
     */
    boolean validate(String customerName);
}
