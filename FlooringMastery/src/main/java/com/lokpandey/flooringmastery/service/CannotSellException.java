/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 10, 2022
 * purpose: Exception when selling is not allowed
 */

package com.lokpandey.flooringmastery.service;


public class CannotSellException extends Exception {

    public CannotSellException(String message) {
        super(message);
    }

    public CannotSellException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
