/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Test class for service layer
 */
package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    public void testParseDate() throws InvalidDataException {
        //ARRANGE
        String dateString1 = "2020-12-20"; //correct format - must be parsed
        String dateString2 = "12-20-2020"; //incorrect format - must raise exception
        //ACT
        LocalDate date1 = service.parseDate(dateString1);
        //ASSERT
        assertTrue(date1 instanceof LocalDate, "Date must be parsed");
        try {
            LocalDate date2 = service.parseDate(dateString2);
            fail("Invalid date exception must be raised");
        } catch(InvalidDataException ide) { }
    }
    
    @Test
    public void testIsFutureDate() {
        //Arrange
        String dateString1 = "2040-12-20";
        String dateString2 = "2020-12-20";
        
        //Act and Assert
        assertTrue(service.isFutureDate(dateString1), "Must be a future date");
        assertFalse(service.isFutureDate(dateString2), "Must be a past date");
    }
    
    @Test
    public void testIsValidCustomerName() {
        //arrange
        String customerName = "";
        //act & assert
        assertFalse(service.isValidCustomerName(customerName), "Customer name cannot be blank");
        customerName = "Acme, Inc.";
        assertTrue(service.isValidCustomerName(customerName), "Must be a valid name");
        customerName = "Joe 123";
        assertFalse(service.isValidCustomerName(customerName), "Customer name cannot have digits");
        
    }

    @Test
    public void testIsValidArea() throws InvalidDataException {
        BigDecimal area = BigDecimal.ZERO;
        try {
            service.isValidArea(area);
            fail("Area cannot be zero");
        } catch(InvalidDataException ide) {}
        area = new BigDecimal("100");
        assertTrue(service.isValidArea(area), "Must be true");
        
    }
    
    //uses ordersDaoStub
    //select all orders
    @Test
    public void testReadOrdersFromFile() {
        
    }
    
    //insert and select order
    @Test
    public void testPlaceAndGetOrder() {
        //Order order = new Order();
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
