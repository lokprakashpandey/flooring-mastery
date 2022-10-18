/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Interface for service layer
 */
package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.model.Order;
import com.lokpandey.flooringmastery.model.Product;
import com.lokpandey.flooringmastery.model.Tax;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    */
    List<Order> readOrdersFromFile(String dateString) 
            throws FileNotFoundException;
    
    /**
     * Returns LocalDate object for the dateString provided
     * 
     * @param String dateString is the date as string from user
     * @return LocalDate if the dateString is valid 
     * @throws InvalidDataException if the dateString is invalid
     */
    public LocalDate parseDate(String dateString) throws InvalidDataException;
    
    /**
     * Checks if the dateString given belongs to a future date.
     *
     * @param String dateString the date for the order
     * @return Boolean true if the dateString is a future date 
     * or false if not
    */
    boolean isFutureDate(String dateString);
    
    /**
     * Checks if the customer name given satisfies a pattern for name.
     * The pattern is it may not be blank, allowed to contain [a-z][0-9] 
     * as well as periods and comma characters. For e.g., "Acme, Inc." is a valid name.
     * 
     * @param String customerName is the name of the customer
     * @return Boolean true if the customer name rule is satisfied 
     * or false if not
     */
    boolean isValidCustomerName(String customerName);
    
    /**
     * Checks if the stateName exists in the taxes file for selling purpose.
     *
     * @param String state the state to check
     * @return Tax tax object the state name is associated with
     * @throws FileNotFoundException when there is problem with reading taxes file
     * @throws CannotSellException if the file does not include the state name
    */
    Tax checkStateAndGetTaxObject(String state) 
                throws FileNotFoundException, CannotSellException;
    
    /**
     * Reads all the products from the Products text file
     *
     * @param void 
     * @return List<Product> if file exists otherwise 
     * @throws FileNotFoundException when file does not exist
    */
    List<Product> readProductList() 
            throws FileNotFoundException;
    
    /**
     * Validates and gets the product object
     *
     * @param String productType  
     * @param List<Product> the productType to check against 
     * @throws InvalidDataException when productType does not 
     * match any product types in productList
    */
    public Product validateAndGetProduct(String productType, List<Product> productList) 
                                            throws InvalidDataException;
    /**
     * Checks if the area given is greater or equal to 100.
     * 
     * @param BigDecimal area is the area input from user
     * @return Boolean true if the area is equal to or more than 100 
     * @throws InvalidDataException when area is negative or less than 100
     */
    boolean isValidArea(BigDecimal area) throws InvalidDataException;
    
    /**
     * Provides next order number from an order string
     * 
     * @param String orderNumberString to parse  
     * @return int representing the order number 
     * @throws InvalidDataException if integer cannot be parsed
     */
    public int parseOrderNumber(String orderNumberString) throws InvalidDataException;
    
    /**
     * Provides next order number for an order
     * 
     * @param void 
     * @return int representing the next available order number 
     * @throws FlooringMasteryPersistenceException if file cannot be read
     */
    int getNextOrderNumber() throws FlooringMasteryPersistenceException;
    
    /**
     * Persists the order to an order file
     * 
     * @param Order order to persist 
     * @param String dateString that is part of file name on which the order to persist
     * @return void  
     * @throws FlooringMasteryPersistenceException if order cannot be placed
     */
    void placeOrder(Order order, String dateString) throws FlooringMasteryPersistenceException;
    
    /**
     * Gets the Order object given date and order number information
     * 
     * @param String dateString that is part of file name on which the order to get
     * @param int orderNumber order number of the Order 
     * @return Order object associated with date and order number
     * @throws FileNotFoundException when no file exists
     * @throws OrderNotFoundException when order number is invalid
     */
    Order getOrder(String dateString, int orderNumber) 
            throws FileNotFoundException, OrderNotFoundException;
    
    /**
     * Updates the order object on the given date
     * 
     * @param Order oldOrder to be updated from
     * @param Order newOrder to be updated to
     * @param String dateString part of orders file to be persisted
     * @return void 
     * @throws FlooringMasteryPersistenceException when there is problem with persisting the update
     */
    public void updateOrder(Order oldOrder, Order newOrder, String dateString) 
            throws FlooringMasteryPersistenceException;
    
    /**
     * Removes the order object on the given date from the file
     * 
     * @param Order order to be deleted
     * @param String dateString part of orders file to be deleted from
     * @return void 
     * @throws FlooringMasteryPersistenceException when there is problem with deleting the order
     */
    public void removeOrder(Order order, String dateString) 
            throws FlooringMasteryPersistenceException;
    
    
    /**
     * Exports all the orders data from the different orders files into a single file
     * and returns the sorted list of those orders
     * @param void 
     * @return List<Order> from different orders files 
     * @throws FlooringMasteryPersistenceException when export file cannot be created 
    */
    public List<Order> exportAllOrders() 
            throws FlooringMasteryPersistenceException;
    
}
