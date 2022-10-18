/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Implementation of the FlooringMasteryOrdersDao interface
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringMasteryOrdersDaoFileImpl implements FlooringMasteryOrdersDao {

    private final String ORDERS_FOLDER;
    private static final String DELIMITER = ",";

    public FlooringMasteryOrdersDaoFileImpl() {
        ORDERS_FOLDER = "Orders/";
    }

    public FlooringMasteryOrdersDaoFileImpl(String ORDERS_FOLDER) {
        this.ORDERS_FOLDER = ORDERS_FOLDER;
    }

    @Override
    public List<Order> selectAllFromOrders(String fileName)
            throws FileNotFoundException {
        String filePath = ORDERS_FOLDER + fileName;
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(filePath)));
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("The file " + fileName + " does not exist");
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // order holds the most recent student unmarshalled
        Order order;
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
            orders.add(order);
            
            // Put order into the orders list
        }
        // close scanner
        scanner.close();
        return orders;
    }

    private Order unmarshallOrder(String orderAsText)  {
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
        Order order = null;
        try {
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

            order = new Order(orderNumber, customerName, state, taxRate,
                productType, area, costPerSquareFoot, laborCostPerSquareFoot,
                materialCost, laborCost, tax, total);
        } catch(NumberFormatException nfe) {}
        return order;

    }

    @Override
    public int selectOrderNumber() throws FlooringMasteryPersistenceException {
        String filePath = "Order_Number_Tracker.txt";
        int nextOrderNumber;
        if (Files.exists(Paths.get(filePath))) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(filePath)));
            } catch (FileNotFoundException ex) {
                throw new FlooringMasteryPersistenceException("The order number be read");
            }
            String currentOrderNumberString = scanner.nextLine();
            nextOrderNumber = Integer.valueOf(currentOrderNumberString) + 1;
            scanner.close();
        } else {
            nextOrderNumber = 1;
        }
        return nextOrderNumber;
    }

    private void createOrderNumberTrackerFile(int currentOrderNumber)
            throws FlooringMasteryPersistenceException {

        String filePath = "Order_Number_Tracker.txt";
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save order number.", e);
        }

        out.println(currentOrderNumber);
        out.flush();
    }

    @Override
    public void createOrder(Order order, String fileName)
            throws FlooringMasteryPersistenceException {
        String ordersFilePath = ORDERS_FOLDER + fileName;
        PrintWriter out;
        if (!Files.exists(Paths.get(ordersFilePath))) {
            try {
                out = new PrintWriter(new FileWriter(ordersFilePath));
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
                        + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                        + "LaborCost,Tax,Total");
                out.flush();
            } catch (IOException ex) {
                throw new FlooringMasteryPersistenceException("Could not create order file.", ex);
            }
        } else {
            try {
                out = new PrintWriter(new FileWriter(ordersFilePath, true));
            } catch (IOException ex) {
                throw new FlooringMasteryPersistenceException("Could not open order file", ex);
            }
        }

        String orderString = order.getOrderNumber() + ","
                + order.getCustomerName() + ","
                + order.getState() + ","
                + order.getTaxRate() + ","
                + order.getProductType() + ","
                + order.getArea() + ","
                + order.getCostPerSquareFoot() + ","
                + order.getLaborCostPerSquareFoot() + ","
                + order.getMaterialCost() + ","
                + order.getLaborCost() + ","
                + order.getTax() + ","
                + order.getTotal();
        out.println(orderString);
        out.flush();
        createOrderNumberTrackerFile(order.getOrderNumber());
        out.close();
    }

    @Override
    public void persistOrders(List<Order> sortedOrderList, String fileName)
            throws FlooringMasteryPersistenceException {
        String ordersFilePath = ORDERS_FOLDER + fileName;
        PrintWriter out;
        try {
            //blanking the file
            out = new PrintWriter(new FileWriter(ordersFilePath));
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
                    + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                    + "LaborCost,Tax,Total");
            out.flush();
        } catch (IOException ex) {
            throw new FlooringMasteryPersistenceException("Could not create order file.", ex);
        }
        String orderString;
        for (Order sortedOrder : sortedOrderList) {
            orderString = sortedOrder.getOrderNumber() + ","
                    + sortedOrder.getCustomerName() + ","
                    + sortedOrder.getState() + ","
                    + sortedOrder.getTaxRate() + ","
                    + sortedOrder.getProductType() + ","
                    + sortedOrder.getArea() + ","
                    + sortedOrder.getCostPerSquareFoot() + ","
                    + sortedOrder.getLaborCostPerSquareFoot() + ","
                    + sortedOrder.getMaterialCost() + ","
                    + sortedOrder.getLaborCost() + ","
                    + sortedOrder.getTax() + ","
                    + sortedOrder.getTotal();
            out.println(orderString);
            out.flush();
        }
        out.close();
    }
}
