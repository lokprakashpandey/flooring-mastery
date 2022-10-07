/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Class that implements FlooringMasteryServiceLayer interface
 */

package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryBackupDao;
import com.lokpandey.flooringmastery.dao.FlooringMasteryDao;
import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    
    private FlooringMasteryDao dao;
    private FlooringMasteryBackupDao backupDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryBackupDao backupDao) {
        this.dao = dao;
        this.backupDao = backupDao;
    }

    @Override
    public List<Order> readOrdersFromFile(String dateString) 
            throws FileNotFoundException, InvalidDateException {
        String fileName = null;
        
        try {
            LocalDate orderDate = LocalDate.parse(dateString);
            
            String dateParts[] = dateString.split("-");
            
            fileName = "Orders_"+dateParts[1]+dateParts[2]+dateParts[0]+".txt";
        } catch(DateTimeParseException e) {
            throw new InvalidDateException("Invalid date - " + dateString);
        }
        return dao.selectAllFromOrders(fileName);
    }
    
    

}
