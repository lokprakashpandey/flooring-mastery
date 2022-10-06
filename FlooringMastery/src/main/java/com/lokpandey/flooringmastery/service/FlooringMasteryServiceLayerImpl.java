/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Class that implements FlooringMasteryServiceLayer interface
 */

package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryBackupDao;
import com.lokpandey.flooringmastery.dao.FlooringMasteryDao;


public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    
    private FlooringMasteryDao dao;
    private FlooringMasteryBackupDao backupDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryBackupDao backupDao) {
        this.dao = dao;
        this.backupDao = backupDao;
    }
    
    

}
