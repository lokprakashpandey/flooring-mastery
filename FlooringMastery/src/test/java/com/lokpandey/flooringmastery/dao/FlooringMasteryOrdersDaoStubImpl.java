/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation for orders dao
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {

    //map of filename as key and orders as values
    public Map<String, List<Order>> ordersMap = new HashMap<>();
    public int currentMaxOrderNumber = 3;

    public FlooringMasteryOrdersDaoStubImpl() {
        Order order1 = new Order(1, "Ada Lovelace",
                 "CA", new BigDecimal("25.00"),
                 "Tile", new BigDecimal("249.00"),
                 new BigDecimal("3.50"), new BigDecimal("4.15"),
                 new BigDecimal("871.50"), new BigDecimal("1033.35"),
                 new BigDecimal("476.21"), new BigDecimal("2381.06"));
        Order order2 = new Order(2, "Doctor Who",
                 "WA", new BigDecimal("9.25"),
                 "Wood", new BigDecimal("243.00"),
                 new BigDecimal("5.15"), new BigDecimal("4.75"),
                 new BigDecimal("1251.45"), new BigDecimal("1154.25"),
                 new BigDecimal("216.51"), new BigDecimal("2622.21"));
        Order order3 = new Order(3, "Albert Einstein",
                 "KY", new BigDecimal("6.00"),
                 "Carpet", new BigDecimal("217.00"),
                 new BigDecimal("2.25"), new BigDecimal("2.10"),
                 new BigDecimal("488.25"), new BigDecimal("455.70"),
                 new BigDecimal("56.64"), new BigDecimal("1000.59"));
        List<Order> list1 = new ArrayList<>();
        list1.add(order1);
        List<Order> list2 = new ArrayList<>();
        list2.add(order2);
        list2.add(order3);
        ordersMap.put("Orders_06012013.txt", list1);
        ordersMap.put("Orders_06022013.txt", list2);
    }

    @Override
    public List<Order> selectAllFromOrders(String fileName) throws FileNotFoundException {
        boolean found = false;
        Set<String> keys = ordersMap.keySet();
        for(String key: keys) {
            if(key.equalsIgnoreCase(fileName)) {
                found = true;
                break;
            }
        }
        if(found)   return ordersMap.get(fileName);
        else        throw new FileNotFoundException("File does not exist");
    }

    @Override
    public int selectOrderNumber() throws FlooringMasteryPersistenceException {
        return currentMaxOrderNumber + 1;
    }

    @Override
    public void createOrder(Order order, String fileName) throws FlooringMasteryPersistenceException {
        if (order != null) {
            //persist order to file named fileName
            List<Order> list = new ArrayList<>();
            Set<String> keys = ordersMap.keySet();
            for(String key: keys) {
                if(key.equalsIgnoreCase(fileName)) {
                    list = ordersMap.get(fileName);
                    break;
                }
            }
            list.add(order);
            ordersMap.put(fileName, list);
            currentMaxOrderNumber = order.getOrderNumber();
            //this number should be preserved for subsequent orders
        }
    }

    @Override
    public void persistOrders(List<Order> orderList, String fileName) throws FlooringMasteryPersistenceException {
        //write new list to file
        ordersMap.replace(fileName, orderList);
    }

}
