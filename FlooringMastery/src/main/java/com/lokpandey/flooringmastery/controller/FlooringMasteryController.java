/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Controller for the FlooringMastery App
 */

package com.lokpandey.flooringmastery.controller;

import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.model.Order;
import com.lokpandey.flooringmastery.model.Product;
import com.lokpandey.flooringmastery.model.Tax;
import com.lokpandey.flooringmastery.service.CannotSellException;
import com.lokpandey.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.lokpandey.flooringmastery.service.InvalidDataException;
import com.lokpandey.flooringmastery.service.OrderNotFoundException;
import com.lokpandey.flooringmastery.view.FlooringMasteryView;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryController {
    
    private final FlooringMasteryView view;
    private final FlooringMasteryServiceLayerImpl service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayerImpl service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAllData();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
        
    private int getMenuSelection() {
        return view.displayMenuAndGetSelection();
    }
    
    private String getValidDate() {
        String dateString;
        while(true) {
            try {
                dateString = view.getDateInfo();
                //if the method throws Exception, then the dateString is invalid 
                //otherwise it is valid and the loop terminates
                if(service.parseDate(dateString) instanceof LocalDate) break;
            } catch(InvalidDataException ide) {
                view.displayErrorMessage(ide.getMessage());
            }
        }
        return dateString;
    }
    
    private void displayOrders() {
        String dateString = getValidDate();
        try {
            List<Order> orderList = service.readOrdersFromFile(dateString);
            view.displayOrderList(orderList);
        } catch(FileNotFoundException fnfe) {
            view.displayErrorMessage(fnfe.getMessage());
            view.waitForKeyPress();
        }
    }
    
    private String getValidFutureDate() {
        String dateString;
        do {
            //get valid date
            dateString = getValidDate();
            //check if the date is in future
            if(service.isFutureDate(dateString)) break;
            else view.displayErrorMessage("Date must be in future"); 
        } while(true);
        return dateString;
    }
    
    private String getValidCustomerName() {
        String customerName;
        while(true) {
            customerName = view.getCustomerName();
            if(service.isValidCustomerName(customerName)) break;
            else view.displayErrorMessage("Invalid name. Please make sure that name may not "
                                  + "be blank and only spaces, periods and commas are allowed");
        }
        return customerName;
    }
    
    private Tax inputValidStateAndGetTaxObject() {
        String state;
        Tax tax;
        while(true) {
            state = view.getState();
            try {
                tax = service.checkStateAndGetTaxObject(state);
                break;
            } catch(FileNotFoundException | CannotSellException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        return tax;
    }
    
    private Product inputProductTypeAndGetProductObject() {
        Product product;
        while(true) {
            List<Product> productList;
            try {
                productList = service.readProductList();
                String productType = view.displayAndGetProductChoice(productList);
                product = service.validateAndGetProduct(productType, productList);
                break;
            } catch(FileNotFoundException | InvalidDataException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        return product;
    }
    
    private BigDecimal getValidArea() {
        BigDecimal area;
        while(true) {
            try {
                area = new BigDecimal(view.getArea());
                if(service.isValidArea(area)) break;
            } catch(NumberFormatException nfe) {
                view.displayErrorMessage("Please enter decimal number");
            } catch(InvalidDataException ide) {
                view.displayErrorMessage(ide.getMessage());
            }     
        }
        return area;
    }
    
    private void addOrder() {
        /* Order date */
        String dateString = getValidFutureDate();
        /* Customer name*/
        String customerName = getValidCustomerName(); 
        /* State */
        Tax tax = inputValidStateAndGetTaxObject();
        /* Product Type */
        Product product = inputProductTypeAndGetProductObject();
        /* Area */
        BigDecimal area = getValidArea();
        try {
            //create order
            Order order = new Order(service.getNextOrderNumber(),
                                        customerName,
                                        tax.getStateAbbreviation(),
                                        tax.getTaxRate(),
                                        product.getProductType(),
                                        area,
                                        product.getCostPerSquareFoot(),
                                        product.getLaborCostPerSquareFoot());
            view.displayOrder(order);
            String choice = view.askForPlacingOrder();
            if(choice.equalsIgnoreCase("Y")) {
                service.placeOrder(order, dateString);
                view.displayCreateSuccessBanner();
            }
        } catch (FlooringMasteryPersistenceException fne) {
            view.displayErrorMessage(fne.getMessage());
        } 
    }
    
    private int getValidOrderNumber() {
        /* Order Number */
        int orderNumber;
        while(true) {
            try {
                String orderNumberString = view.getOrderNumber();
                orderNumber = service.parseOrderNumber(orderNumberString); 
                break;
            } catch(InvalidDataException ide) {
                view.displayErrorMessage(ide.getMessage());
            }
        }
        return orderNumber;
    }
    
    private void editOrder() { 
        /* Date */
        String dateString = getValidDate();
        /* Order Number */
        int orderNumber = getValidOrderNumber();
        /* Order */
        try {
            Order oldOrder = service.getOrder(dateString, orderNumber);
            
            //for creating new Order object when there is no exception
            String newCustomerName;
            String newState;
            BigDecimal newTaxRate;
            String newProductType;
            BigDecimal newArea;
            BigDecimal newCostPerSquareFoot;
            BigDecimal newLaborCostPerSquareFoot;
            
            /* Customer name */
            while(true) {
                String oldCustomerName = oldOrder.getCustomerName();
                newCustomerName = view.getCustomerName(oldCustomerName);
                //if user hits enter key
                if(newCustomerName.isEmpty()) {
                    newCustomerName = oldCustomerName;
                    break;
                }
                else {
                    //If user types customer name, check for its its validity. 
                    //If valid break, otherwise loop again
                    if(service.isValidCustomerName(newCustomerName)) break;
                    else {
                        view.displayErrorMessage("Invalid name. Try again.");
                    }
                }
            }
            
            /* State */
            while(true) {
                newState = view.getState(oldOrder.getState());
                if(newState.isEmpty()) {
                    newState = oldOrder.getState();
                    newTaxRate = oldOrder.getTaxRate();
                    break;
                }
                else {
                    try {
                        Tax tax = service.checkStateAndGetTaxObject(newState);
                        newTaxRate = tax.getTaxRate();
                        break;
                    } catch (CannotSellException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                }
            }
            
            /* Product Type */
            while(true) {
                newProductType = view.displayAndGetProductChoice(service.readProductList(), 
                                                                    oldOrder.getProductType());
                if(newProductType.isEmpty()) {
                    newProductType = oldOrder.getProductType();
                    newCostPerSquareFoot = oldOrder.getCostPerSquareFoot();
                    newLaborCostPerSquareFoot = oldOrder.getLaborCostPerSquareFoot();
                    break;
                } else {
                    try {
                        Product product = service.validateAndGetProduct(newProductType, 
                                                                        service.readProductList());
                        newCostPerSquareFoot = product.getCostPerSquareFoot();
                        newLaborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
                        break;
                    } catch (InvalidDataException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                }
            }
            
            /* Area */
            while(true) {
                String newAreaString = view.getArea(oldOrder.getArea());
                if(newAreaString.isEmpty()) {
                    newArea = oldOrder.getArea();
                    break;
                } else {
                    try{
                        newArea = new BigDecimal(newAreaString);
                        if(service.isValidArea(newArea)) break;
                    } catch(NumberFormatException | InvalidDataException e) {
                        view.displayErrorMessage(e.getMessage());
                    }
                }
            }
            
            /* Create new Order object*/
            Order newOrder = new Order(orderNumber, 
                                        newCustomerName, 
                                        newState, 
                                        newTaxRate, 
                                        newProductType, 
                                        newArea, 
                                        newCostPerSquareFoot, 
                                        newLaborCostPerSquareFoot);
            
            view.displayOrder(newOrder);
            String choice = view.askForPlacingOrder();
            if(choice.equalsIgnoreCase("Y")) {
                service.updateOrder(oldOrder, newOrder, dateString);
                view.displayUpdateSuccessBanner();
            }
            
        } catch(FileNotFoundException 
                    | OrderNotFoundException 
                    | FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            view.waitForKeyPress();
        }
    }
    
    private void removeOrder() {
        String dateString = getValidDate();
        int orderNumber = getValidOrderNumber();
        try {
            Order order = service.getOrder(dateString, orderNumber);
            view.displayOrder(order);
            String choice = view.askForRemovingOrder();
            if(choice.equalsIgnoreCase("Y")) {
                service.removeOrder(order, dateString);
                view.displayRemoveSuccessBanner();
            }
        } catch(FileNotFoundException | OrderNotFoundException 
                                        | FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            view.waitForKeyPress();
        } 
    }
    
    private void exportAllData() {
        try {
            service.exportAllOrders();
            view.displayExportSuccessBanner();
        } catch(FlooringMasteryPersistenceException fmpe) {
            view.displayErrorMessage(fmpe.getMessage());
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
