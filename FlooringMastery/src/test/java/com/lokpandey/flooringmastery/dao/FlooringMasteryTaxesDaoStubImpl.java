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
import java.math.BigDecimal;


public class FlooringMasteryTaxesDaoStubImpl implements FlooringMasteryTaxesDao {

    @Override
    public Tax selectFromTax(String state) throws FileNotFoundException, CannotSellException {
        if(state.equalsIgnoreCase("TX")) {
            return new Tax("TX", "Texas", new BigDecimal("4.45"));
        }
        else if(state.equalsIgnoreCase("WA")) {
            return new Tax("WA", "Washington", new BigDecimal("9.25"));
        }
        else if(state.equalsIgnoreCase("KY")) {
            return new Tax("KY", "Kentucky", new BigDecimal("6.00"));
        }
        else if(state.equalsIgnoreCase("CA")) {
            return new Tax("CA", "California", new BigDecimal("25.00"));
        }
        else {
            throw new CannotSellException("Cannot sell here");
        }
    }

}
