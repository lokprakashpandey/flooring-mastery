/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Exception while persisting order
 */

package com.lokpandey.flooringmastery.dao;


public class FlooringMasteryPersistenceException extends Exception {

    public FlooringMasteryPersistenceException(String message) {
        super(message);
    }

    public FlooringMasteryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    

}
