/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation for taxes dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Tax;
import com.lokpandey.flooringmastery.service.CannotSellException;
import java.io.FileNotFoundException;


public class FlooringMasteryTaxesDaoStubImpl implements FlooringMasteryTaxesDao {

    @Override
    public Tax selectFromTax(String state) throws FileNotFoundException, CannotSellException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
