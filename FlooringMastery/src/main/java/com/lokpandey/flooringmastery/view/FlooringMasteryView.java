/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: View for FlooringMastery Console App
 */

package com.lokpandey.flooringmastery.view;

import com.lokpandey.flooringmastery.model.Order;
import java.lang.reflect.Field;
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

        return io.readInt("Please select from the above choices:", 1, 6);
    }
    
    public String getDateInfo() {
        return io.readString("Please enter date (yyyy-MM-dd):");
    }
    
    public void displayOrderList(List<Order> list) {
        if(list == null) io.print("No any orders exist");
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
