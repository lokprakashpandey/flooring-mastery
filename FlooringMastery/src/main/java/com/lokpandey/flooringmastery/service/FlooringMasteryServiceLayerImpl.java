/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Class that implements FlooringMasteryServiceLayer interface
 */
package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryBackupDao;
import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.lokpandey.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.dao.FlooringMasteryProductsDao;
import com.lokpandey.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.lokpandey.flooringmastery.model.Product;
import com.lokpandey.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private final FlooringMasteryOrdersDao ordersDao;
    private final FlooringMasteryTaxesDao taxesDao;
    private final FlooringMasteryBackupDao backupDao;
    private final FlooringMasteryProductsDao productsDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrdersDao ordersDao, FlooringMasteryTaxesDao taxesDao, FlooringMasteryBackupDao backupDao, FlooringMasteryProductsDao productsDao) {
        this.ordersDao = ordersDao;
        this.taxesDao = taxesDao;
        this.backupDao = backupDao;
        this.productsDao = productsDao;
    }

    @Override
    public List<Order> readOrdersFromFile(String dateString)
            throws FileNotFoundException {
        String fileName = getFileName(dateString);
        return ordersDao.selectAllFromOrders(fileName);
    }

    private String getFileName(String dateString) {
        String dateParts[] = dateString.split("-");
        String fileName = "Orders_" + dateParts[1] + dateParts[2] + dateParts[0] + ".txt";
        return fileName;
    }

    @Override
    public boolean isFutureDate(String dateString) {
        return LocalDate.parse(dateString).isAfter(LocalDate.now());
    }

    //parses date if datestring is valid otherwise throws an exception
    @Override
    public LocalDate parseDate(String dateString) throws InvalidDataException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidDataException("Invalid date - " + dateString);
        }
        return date;
    }

    @Override
    public boolean isValidCustomerName(String customerName) {
        //Since there is plus, the name cannot be blank
        String regex = "^[a-zA-Z]+[ ,.a-zA-Z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(customerName);
        return matcher.matches();
    }

    @Override
    public Tax checkStateAndGetTaxObject(String state)
            throws FileNotFoundException, CannotSellException {
        return taxesDao.selectFromTax(state);
    }

    @Override
    public List<Product> readProductList() throws FileNotFoundException {
        return productsDao.selectAllFromProducts();
    }

    @Override
    public Product validateAndGetProduct(String productType, List<Product> productList)
            throws InvalidDataException {
        boolean valid = false;
        Product validProduct = null;
        for (Product product : productList) {
            if (product.getProductType().equalsIgnoreCase(productType)) {
                valid = true;
                validProduct = product;
                break;
            }
        }
        if (valid) {
            return validProduct;
        } else {
            throw new InvalidDataException("Product type not valid");
        }
    }

    @Override
    public boolean isValidArea(BigDecimal area) throws InvalidDataException {
        if (area.compareTo(new BigDecimal("100")) >= 0) {
            return true;
        } else {
            throw new InvalidDataException("Area cannot be less than 100");
        }
    }

    @Override
    public int getNextOrderNumber() throws FlooringMasteryPersistenceException {
        return ordersDao.selectOrderNumber();
    }

    @Override
    public void placeOrder(Order order, String dateString) throws FlooringMasteryPersistenceException {
        String fileName = getFileName(dateString);
        ordersDao.createOrder(order, fileName);
    }

    @Override
    public int parseOrderNumber(String orderNumberString) throws InvalidDataException {
        int orderNumber;
        try {
            orderNumber = Integer.parseInt(orderNumberString);
        } catch (NumberFormatException nfe) {
            throw new InvalidDataException("Cannot parse order number");
        }
        if (orderNumber <= 0) {
            throw new InvalidDataException("Invalid order number");
        }
        return orderNumber;
    }

    @Override
    public Order getOrder(String dateString, int orderNumber)
            throws FileNotFoundException, OrderNotFoundException {
        Order foundOrder = null;
        List<Order> orderList = readOrdersFromFile(dateString);
        for (Order order : orderList) {
            if (order != null && order.getOrderNumber() == orderNumber) {
                foundOrder = order;
                break;
            }
        }
        if (foundOrder == null) {
            throw new OrderNotFoundException("Cannot find the order");
        } else {
            return foundOrder;
        }
    }

    //For updating order, I read the orders on that date into a list,
    //then update the list in memory for the particular order and finally
    //rewrite the file with the updated list
    @Override
    public void updateOrder(Order oldOrder, Order newOrder, String dateString)
            throws FlooringMasteryPersistenceException {
        try {
            List<Order> orderList = readOrdersFromFile(dateString);
            orderList.remove(oldOrder);
            orderList.add(newOrder);
            //sort my list using streams
            List<Order> sortedOrderList = orderList.stream()
                    .sorted(Comparator.comparing(Order::getOrderNumber))
                    .collect(Collectors.toList());
            String fileName = getFileName(dateString);
            ordersDao.persistOrders(sortedOrderList, fileName);
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryPersistenceException("Problem with updating order", ex);
        }
    }

    @Override
    public void removeOrder(Order order, String dateString)
            throws FlooringMasteryPersistenceException {
        try {
            List<Order> orderList = readOrdersFromFile(dateString);
            orderList.remove(order);
            String fileName = getFileName(dateString);
            ordersDao.persistOrders(orderList, fileName);
        } catch (FileNotFoundException fnfe) {
            throw new FlooringMasteryPersistenceException("Problem with removing order");
        }
    }

    @Override
    public List<Order> exportAllOrders() 
            throws FlooringMasteryPersistenceException {
        return backupDao.exportAllOrders();
    }

}
