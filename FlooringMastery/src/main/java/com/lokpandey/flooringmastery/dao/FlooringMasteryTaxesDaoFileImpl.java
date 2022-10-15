/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 10, 2022
 * purpose: Taxes dao file implementation
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Tax;
import com.lokpandey.flooringmastery.service.CannotSellException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Scanner;


public class FlooringMasteryTaxesDaoFileImpl implements FlooringMasteryTaxesDao {

    private final String TAXES_FOLDER;
    private static final String DELIMITER = ",";

    public FlooringMasteryTaxesDaoFileImpl() {
        TAXES_FOLDER = "Data/";
    }

    public FlooringMasteryTaxesDaoFileImpl(String TAXES_FOLDER) {
        this.TAXES_FOLDER = TAXES_FOLDER;
    }
        
    @Override
    public Tax selectFromTax(String state) 
            throws FileNotFoundException, CannotSellException {
        
        Scanner scanner;
        String filePath = TAXES_FOLDER + "Taxes.txt";
        try {
            // Create Scanner for reading the file
            scanner = new Scanner( 
                    new BufferedReader(
                            new FileReader(filePath)));
        } catch (FileNotFoundException fnfe) {
           throw new FileNotFoundException("The file " + filePath + " does not exist");
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // tax is the most recent tax record unmarshalled
        Tax tax = null;
        boolean found = false;
        scanner.nextLine(); // Skip the first line
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a tax object
            tax = unmarshallTax(currentLine);
            
            if(tax.getStateAbbreviation().equalsIgnoreCase(state)) {
                found = true;
                break;
            }
            
        }
        // close scanner
        scanner.close();
        
        if(found) return tax;
        else throw new CannotSellException("Tax record for " + state + 
                                            " not found, cannot sell here");
    }
    
    private Tax unmarshallTax(String taxAsText) {
        // taxAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // TX,Texas,4.45
        //
        // We then split that line on our DELIMITER - which we are using as ,
        // Leaving us with an array of Strings, stored in taxTokens.
        // Which should look like this:
        // _______________
        // |  |     |    |
        // |TX|Texas|4.45|
        // |  |     |    |
        // ---------------
        // [0]  [1]   [2] 
        String[] taxTokens = taxAsText.split(DELIMITER);
        String state = taxTokens[0];
        String stateName = taxTokens[1];
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);
        
        return new Tax(state, stateName, taxRate);
    }

}
