/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Controller for the FlooringMastery App
 */

package com.lokpandey.flooringmastery.controller;

import com.lokpandey.flooringmastery.model.Order;
import com.lokpandey.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.lokpandey.flooringmastery.service.InvalidDateException;
import com.lokpandey.flooringmastery.view.FlooringMasteryView;
import java.io.FileNotFoundException;
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
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (/*FlooringMasteryPersistenceException*/ Exception fmpe) {
            view.displayErrorMessage(fmpe.getMessage());
        }
    }
        
    private int getMenuSelection() {
        return view.displayMenuAndGetSelection();
    }
    
    private void displayOrders() {
        
        String dateString = null;
        List<Order> orderList = null;
        boolean repeatAgain;
        do {
            dateString = view.getDateInfo();
            try {
                orderList = service.readOrdersFromFile(dateString);
                repeatAgain = false;
            } catch(InvalidDateException ide) {
                view.displayErrorMessage(ide.getMessage());
                repeatAgain = true;
            } catch(FileNotFoundException fnfe) {
                view.displayErrorMessage(fnfe.getMessage());
                repeatAgain = false;
            }
        }while(repeatAgain);
        
        view.displayOrderList(orderList);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
