/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Controller for the FlooringMastery App
 */

package com.lokpandey.flooringmastery.controller;

import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.lokpandey.flooringmastery.view.FlooringMasteryView;


public class FlooringMasteryController {
    
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayerImpl service;

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
        } catch (/*FlooringMasteryPersistenceException*/ Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
        
    }
        
    private int getMenuSelection() {
        return view.displayMenuAndGetSelection();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
