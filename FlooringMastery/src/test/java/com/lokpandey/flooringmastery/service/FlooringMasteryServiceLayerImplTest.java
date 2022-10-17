/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Test class for service layer
 */
package com.lokpandey.flooringmastery.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lokpandey
 */
public class FlooringMasteryServiceLayerImplTest {
    
    private final FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerImplTest() {
        ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayerImpl.class);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testParseDate() {
        
    }
    
    @Test
    public void testIsFutureDate() {
    }
    
    @Test
    public void testIsValidCustomerName() {
    }

    @Test
    public void testIsValidArea() {
    }
    
    //uses ordersDaoStub
    //select all orders
    @Test
    public void testReadOrdersFromFile() {
    }
    
    //select one order
    @Test
    public void testGetOrder() {
        
    }
    
    //insert an order
    @Test
    public void testPlaceOrder() {
        
    }
    
    //update an order
    @Test
    public void testUpdateOrder() {
    }
    
    //delete an order
    @Test
    public void testRemoveOrder() {
    }
    
    //uses backupDaoStub
    @Test
    public void testExportAllOrders() {
    
    }
    
    //uses productsDaoStub
    @Test
    public void testReadProductList() {
    }
    
    @Test
    public void testValidateAndGetProduct() {
    }
    
    //uses taxesDaoStub
    @Test
    public void checkStateAndGetTaxObject() {
        
    }
    
    //testing tracker file
    @Test
    public void testGetOrderNumber() {
        
    }
    
    @Test
    public void testParseOrderNumber() {
        
    }
    
}
