/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Implementation of the FlooringMasteryDao interface
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private final String ORDERS_FOLDER;
    private static final String DELIMITER = ",";
    
    public FlooringMasteryDaoFileImpl() {
        ORDERS_FOLDER = "Orders/";
    }

    public FlooringMasteryDaoFileImpl(String ORDERS_FOLDER) {
        this.ORDERS_FOLDER = ORDERS_FOLDER;
    }
    
    @Override
    public List<Order> selectAllFromOrders(String fileName) 
            throws FileNotFoundException {
        
        String filePath = ORDERS_FOLDER+fileName;
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner( 
                    new BufferedReader(
                            new FileReader(filePath)));
        } catch (FileNotFoundException fnfe) {
           throw fnfe;
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // order holds the most recent student unmarshalled
        Order order = null;
        List<Order> orders = new ArrayList<>();
        // Go through file line by line, decoding each line after first line into an 
        // Order object by calling the unmarshallOrder method.
        // Process while we have more lines in the file
        scanner.nextLine(); // Skip the first line
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an Order
            order = unmarshallOrder(currentLine);
            // Put order into the orders list
            orders.add(order);
        }
        // close scanner
        scanner.close();
        
        return orders;
    }
    
    private Order unmarshallOrder(String orderAsText) {
        // orderAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
        //
        // We then split that line on our DELIMITER - which we are using as ,
        // Leaving us with an array of Strings, stored in orderTokens.
        // Which should look like this:
        // _____________________________________________________________________________
        // | |            |  |     |    |      |    |    |      |       |      |       |
        // |1|Ada Lovelace|CA|25.00|Tile|249.00|3.50|4.15|871.50|1033.35|476.21|2381.06|
        // | |            |  |     |    |      |    |    |      |       |      |       |
        // -----------------------------------------------------------------------------
        // [0]     [1]     [2] [3]  [4]    [5]   [6]  [7]   [8]    [9]    [10]    [11]
        String[] orderTokens = orderAsText.split(DELIMITER);
        Integer orderNumber = Integer.parseInt(orderTokens[0]);
        String customerName = orderTokens[1];
        String state = orderTokens[2];
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        String productType = orderTokens[4];
        BigDecimal area = new BigDecimal(orderTokens[5]);
        BigDecimal costPerSquareFoot = new BigDecimal(orderTokens[6]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(orderTokens[7]);
        BigDecimal materialCost = new BigDecimal(orderTokens[8]);
        BigDecimal laborCost = new BigDecimal(orderTokens[9]);
        BigDecimal tax = new BigDecimal(orderTokens[10]);
        BigDecimal total = new BigDecimal(orderTokens[11]);
        
        Order order = new Order(orderNumber, customerName, state, taxRate, 
                                productType, area, costPerSquareFoot, laborCostPerSquareFoot,
                                materialCost, laborCost, tax, total);
        return order;
        
    }

    
}
