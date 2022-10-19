/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation of back up dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class FlooringMasteryBackupDaoStubImpl implements FlooringMasteryBackupDao {

    FlooringMasteryOrdersDao ordersDao;

    public FlooringMasteryBackupDaoStubImpl(FlooringMasteryOrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }
    
    @Override
    public List<Order> exportAllOrders() 
            throws FlooringMasteryPersistenceException {
        Set<String> fileNames = ((FlooringMasteryOrdersDaoStubImpl)ordersDao).ordersMap.keySet();
        List<Order> allOrders = new ArrayList<>();
        List<Order> tempList;
        for(String fileName: fileNames) {
            try {
                tempList = ordersDao.selectAllFromOrders(fileName);
                allOrders.addAll(tempList);
            } catch(FileNotFoundException fnfe) {}
        }
        return allOrders;
    }
}
