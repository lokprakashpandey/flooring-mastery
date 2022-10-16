/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation for orders dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.util.List;


public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {

    @Override
    public List<Order> selectAllFromOrders(String fileName) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int selectOrderNumber() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void createOrder(Order order, String fileName) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void createOrders(List<Order> order, String fileName) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
