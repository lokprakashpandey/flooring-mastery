/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 11, 2022
 * purpose: Class to read products file
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao {

    private final String PRODUCTS_FOLDER;
    private static final String DELIMITER = ",";

    public FlooringMasteryProductsDaoFileImpl() {
        PRODUCTS_FOLDER = "Data/";
    }
    
    public FlooringMasteryProductsDaoFileImpl(String PRODUCTS_FOLDER) {
        this.PRODUCTS_FOLDER = PRODUCTS_FOLDER;
    }
    
    @Override
    public List<Product> selectAllFromProducts() throws FileNotFoundException {
        Scanner scanner;
        String filePath = PRODUCTS_FOLDER + "Products.txt"; 
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
        List<Product> productList = new ArrayList<>();
        // product is the most recent Product record unmarshalled
        Product product;
        scanner.nextLine(); // Skip the first line
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Product object
            product = unmarshallProduct(currentLine);
            productList.add(product);
            
        }
        // close scanner
        scanner.close();
        return productList;
    }
    
    private Product unmarshallProduct(String productAsText) {
        // productAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Tile,3.50,4.15
        //
        // We then split that line on our DELIMITER - which we are using as ,
        // Leaving us with an array of Strings, stored in productTokens.
        // Which should look like this:
        // _______________
        // |    |    |    |
        // |Tile|3.50|4.15|
        // |    |    |    |
        // ---------------
        // [0]  [1]   [2] 
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        
        return new Product(productType, costPerSquareFoot, laborCostPerSquareFoot);
    }

}
