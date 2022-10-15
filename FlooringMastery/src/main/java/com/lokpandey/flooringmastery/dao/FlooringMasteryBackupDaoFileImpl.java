/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: File implementation of Backup dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FlooringMasteryBackupDaoFileImpl implements FlooringMasteryBackupDao {

    private final String BACKUP_FOLDER;
    FlooringMasteryOrdersDao ordersDao;

    public FlooringMasteryBackupDaoFileImpl() {
        BACKUP_FOLDER = "Backup/";
        ordersDao = new FlooringMasteryOrdersDaoFileImpl();
    }

   //for testing
    public FlooringMasteryBackupDaoFileImpl(String BACKUP_FOLDER, FlooringMasteryOrdersDao ordersDao) {
        this.BACKUP_FOLDER = BACKUP_FOLDER;
        this.ordersDao = ordersDao;
    }
    
    private List<Order> readSingleOrdersFileIntoList(String fileName) throws FileNotFoundException {
        //write complete code for better SOC
       return ordersDao.selectAllFromOrders(fileName);
    }
    
    @Override
    public List<Order> exportAllOrders() throws FlooringMasteryPersistenceException {
        String filePath = BACKUP_FOLDER+"DataExport.txt";
        
        //get all the orders in a list by reading all files in the Orders folder
        List<Order> allOrdersList = new ArrayList<>();
        File folder = new File("Orders/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            try {
            List<Order> tempList = readSingleOrdersFileIntoList(file.getName());
            allOrdersList.addAll(tempList);
            } catch(FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException("Cannot read orders file");
            }
        }
        
        //Now sort the all orders list using stream and lambda
        List<Order> sortedAllOrdersList = allOrdersList.stream()
                                                    .sorted(Comparator.comparing(Order::getOrderNumber))
                                                    .collect(Collectors.toList());
                
        //create export file
        PrintWriter out;
        try { 
            out = new PrintWriter(new FileWriter(filePath));
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,"
                    + "Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                    + "LaborCost,Tax,Total");
            
            //write orders to export file one order/line at a time
            String orderString;
            for(Order order: sortedAllOrdersList) {
                orderString = order.getOrderNumber() + "," +
                                order.getCustomerName() + "," +
                                order.getState() + "," + 
                                order.getTaxRate() + "," +
                                order.getProductType() + "," +
                                order.getArea() + "," +
                                order.getCostPerSquareFoot() + "," +
                                order.getLaborCostPerSquareFoot() + "," +
                                order.getMaterialCost() + "," +
                                order.getLaborCost() + "," +
                                order.getTax() + "," +
                                order.getTotal();
                out.println(orderString);
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            throw new FlooringMasteryPersistenceException("Could not create export file.", ex);
        }        
    return sortedAllOrdersList;
    }
}
