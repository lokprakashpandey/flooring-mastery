/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Interface for orders backup
 */

package com.lokpandey.flooringmastery.dao;


public interface FlooringMasteryBackupDao {
    void exportAllOrders() throws FlooringMasteryPersistenceException;
}
