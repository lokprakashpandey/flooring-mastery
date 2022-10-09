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
            throws FileNotFoundException, InvalidDataException {
        LocalDate orderDate = parseDate(dateString);
            
        String dateParts[] = dateString.split("-");
            
        String fileName = "Orders_"+dateParts[1]+dateParts[2]+dateParts[0]+".txt";
        
        return dao.selectAllFromOrders(fileName);
    }

    @Override
    public boolean isFutureDate(String dateString) throws InvalidDataException {
        LocalDate futureDate = parseDate(dateString);
        return futureDate.isAfter(LocalDate.now());
    }
    
    private LocalDate parseDate(String dateString) throws InvalidDataException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch(DateTimeParseException dtpe) {
            throw new InvalidDataException("Invalid date - " + dateString);
        }
        return date;
    }

    @Override
    public boolean validate(String customerName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
