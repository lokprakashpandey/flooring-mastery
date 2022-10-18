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
public interface FlooringMasteryOrdersDao {
    
    /**
     * Selects all the orders from the file name provided
     *
     * @param String fileName to read orders from
     * @return List<Order> if orders file exists otherwise null
     * @throws FileNotFoundException when file cannot be read
    */
    public List<Order> selectAllFromOrders(String fileName) 
            throws FileNotFoundException;
    
    
    /**
     * Returns the next available order number from file
     *
     * @param void 
     * @return int next available order number
     * @throws FlooringMasteryPersistenceException when order tracker file cannot be read
    */
    public int selectOrderNumber() throws FlooringMasteryPersistenceException;
    
//    /**
//     * Creates the order tracker file and persists the last successful order number into it
//     *
//     * @param int currentOrderNumber to persist to file 
//     * @return void
//     * @throws FlooringMasteryPersistenceException when order tracker file cannot be persisted
//    */
//    public void createFileAndWriteOrderNumber(int currentOrderNumber) 
//            throws FlooringMasteryPersistenceException;
//    
    
    /**
     * Creates the orders file and persists the order information into it
     *
     * @param Order order to persist to file 
     * @param String fileName on which to persist Order
     * @return void 
     * @throws FlooringMasteryPersistenceException when order information cannot be persisted
    */
    public void createOrder(Order order, String fileName) 
            throws FlooringMasteryPersistenceException;
    
    /**
     * Updates the orders file and persists all the orders information into it
     *
     * @param List<Order> list of order to persist to file 
     * @param String fileName on which to persist Order
     * @return void 
     * @throws FlooringMasteryPersistenceException when order information cannot be persisted
    */
    public void persistOrders(List<Order> order, String fileName) 
            throws FlooringMasteryPersistenceException;
    
    
}
