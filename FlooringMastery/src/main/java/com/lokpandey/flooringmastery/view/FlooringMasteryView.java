/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: View for FlooringMastery Console App
 */

package com.lokpandey.flooringmastery.view;

import com.lokpandey.flooringmastery.model.Order;
import com.lokpandey.flooringmastery.model.Product;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;


public class FlooringMasteryView {
    
    private final UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public int displayMenuAndGetSelection() {
        
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please select from the above choices [1-6]: ", 1, 6);
    }
    
    public String getDateInfo() {
        return io.readString("Please enter date (yyyy-MM-dd): ");
    }
    
    public String getCustomerName() {
        return io.readString("Please enter customer name: ");
    }
    
    public String getCustomerName(String customerName) {
        return io.readString("Enter customer name (" + customerName + ") : ");
    }
    
    public String getState() {
        return io.readString("Please enter state : ");
    }
    
    public String getState(String state) {
        return io.readString("Enter state (" + state + ") : ");
    }
    
    public void displayOrderList(List<Order> list) {
        if(list == null) io.print("*** There are no orders ***");
        else {
            String headerString = "";
            //Using reflection to get all the attribute names of Order class 
            //for creating header string
            Field[] fields = Order.class.getDeclaredFields();
                for(Field field: fields) {
                    headerString += field.getName().toUpperCase() + " ";
                }
            io.print(headerString);
            
            //Read the data into a string
            String dataString = "";
            for(Order order: list) {
                dataString += order.getOrderNumber() + "\t";
                dataString += order.getCustomerName() + "\t";
                dataString += order.getState() + "\t";
                dataString += order.getTaxRate()+ "\t";
                dataString += order.getProductType()+ "\t";
                dataString += order.getArea()+ "\t";
                dataString += order.getCostPerSquareFoot()+ "\t\t\t";
                dataString += order.getLaborCostPerSquareFoot()+ "\t\t";
                dataString += order.getMaterialCost()+ "\t\t";
                dataString += order.getLaborCost()+ "\t";
                dataString += order.getTax() + "\t";
                dataString += order.getTotal() + "\n";
            }
            io.print(dataString);
        }
        io.readString("Press a key to continue");
    }
    
    public String displayAndGetProductChoice(List<Product> list) {
            io.print("Please select from the following choices:");
            String headerString = "";
            //Using reflection to get all the attribute names of Product class 
            //for creating header string
            Field[] fields = Product.class.getDeclaredFields();
                for(Field field: fields) {
                    headerString += field.getName().toUpperCase() + " ";
                }
            io.print(headerString);
            //Read the data into a string
            String dataString = "";
            for(int i=0; i<list.size(); i++) {
                dataString += list.get(i).getProductType() + "\t\t";
                dataString += list.get(i).getCostPerSquareFoot() + "\t\t";
                dataString += list.get(i).getLaborCostPerSquareFoot() + "\n";
            }
            io.print(dataString);
        
        return io.readString("Please enter your product type choice: ");
    }
    
    public String displayAndGetProductChoice(List<Product> list, String productType) {
            io.print("Please select from the following choices:");
            String headerString = "";
            //Using reflection to get all the attribute names of Product class 
            //for creating header string
            Field[] fields = Product.class.getDeclaredFields();
                for(Field field: fields) {
                    headerString += field.getName().toUpperCase() + " ";
                }
            io.print(headerString);
            //Read the data into a string
            String dataString = "";
            for(int i=0; i<list.size(); i++) {
                dataString += list.get(i).getProductType() + "\t\t";
                dataString += list.get(i).getCostPerSquareFoot() + "\t\t";
                dataString += list.get(i).getLaborCostPerSquareFoot() + "\n";
            }
            io.print(dataString);
        
        return io.readString("Enter your product type choice ( " + productType + ") : ");
    }
    
    public String getArea() {
        return io.readString("Please enter area: ");
    }
    
    public String getArea(BigDecimal area) {
        return io.readString("Please enter area (" + area + ") : ");
    }
    
    public void displayOrder(Order order) {
        io.print("The order details are:");
        String headerString = "";
        //Using reflection to get all the attribute names of Order class 
        //for creating header string
        Field[] fields = Order.class.getDeclaredFields();
            for(Field field: fields) {
                headerString += field.getName().toUpperCase() + " ";
            }
        io.print(headerString);

        //Read the data into a string
        String dataString = "";
            dataString += order.getOrderNumber() + "\t";
            dataString += order.getCustomerName() + "\t";
            dataString += order.getState() + "\t";
            dataString += order.getTaxRate()+ "\t";
            dataString += order.getProductType()+ "\t";
            dataString += order.getArea()+ "\t";
            dataString += order.getCostPerSquareFoot()+ "\t\t\t";
            dataString += order.getLaborCostPerSquareFoot()+ "\t\t";
            dataString += order.getMaterialCost()+ "\t\t";
            dataString += order.getLaborCost()+ "\t";
            dataString += order.getTax() + "\t";
            dataString += order.getTotal() + "\n";
        io.print(dataString);
        
    }
    
    public String askForPlacingOrder() {
        return io.readString("Do you want to place the order (Type Y/N)? ");
    }
    
    public String askForRemovingOrder() {
        return io.readString("Are you sure to remove the order (Type Y/N)? ");
    }
    
    public String getOrderNumber() {
        return io.readString("Please enter the order number: ");
    }
    
    public void displayCreateSuccessBanner() {
        io.print("Order created successfully");
        io.readString("Press any key to continue");
    }
    
    public void displayUpdateSuccessBanner() {
        io.print("Order updated successfully");
        io.readString("Press any key to continue");
    }
    
    public void displayRemoveSuccessBanner() {
        io.print("Order deleted successfully");
        io.readString("Press any key to continue");
    }
    
    public void waitForKeyPress() {
        io.readString("Press any key to continue");
    }
    
    public void displayExportSuccessBanner() {
        io.print("All data exported successfully");
        io.readString("Press any key to continue");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR: " + errorMsg);
        
    }
}
