/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 10, 2022
 * purpose: Taxes dao interface
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Tax;
import com.lokpandey.flooringmastery.service.CannotSellException;
import java.io.FileNotFoundException;

/**
 *
 * @author lokpandey
 */
public interface FlooringMasteryTaxesDao {
    
    /**
     * Selects the Tax for the state provided 
     *
     * @param String state to find the Tax object 
     * @return Tax if state exists in the file
     * @throws FileNotFoundException when file cannot be read
     * @throws CannotSellException when state name does not exist in file
    */
    Tax selectFromTax(String state) throws FileNotFoundException, CannotSellException;
}
