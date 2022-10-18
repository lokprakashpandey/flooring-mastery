/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Test class for service layer
 */
package com.lokpandey.flooringmastery.service;

import com.lokpandey.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
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
    
//    @BeforeEach
//    public void setUp() {
//    }
//    
    @AfterEach
    public void tearDown() throws IOException {
//        Path path = Paths.get("Orders/Orders_12202040.txt");
//        if(Files.exists(path)) {
//            Files.delete(path);
//        }
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
    public void testReadOrdersFromFile() throws FileNotFoundException {
        //arrange
        Order order = new Order(1                        , "Ada Lovelace"
                                , "CA"                     , new BigDecimal("25.00")
                                , "Tile"                   , new BigDecimal("249.00")
                                , new BigDecimal("3.50")   , new BigDecimal("4.15")
                                , new BigDecimal("871.50") , new BigDecimal("1033.35")
                                , new BigDecimal("476.21") , new BigDecimal("2381.06"));
        //act
        List<Order> orderList = service.readOrdersFromFile("2013-06-01");
        //assert
        assertEquals(orderList.size(), 1, "The size must be 1");
        assertTrue(orderList.contains(order), "Thr order must be in the list");
    }
    
    //insert order
    @Test
    public void testPlaceOrderAndGetOrderNumber() throws FlooringMasteryPersistenceException {
        //arrange valid data
        Order order = new Order(4                          , "Some name"
                                , "TX"                     , new BigDecimal("4.45")
                                , "Tile"                   , new BigDecimal("100.00")
                                , new BigDecimal("3.50")   , new BigDecimal("4.15")
                                );
        //act
        service.placeOrder(order, "2040-12-20");
        //if order is placed then the max order number is updated, so assert it
        int orderNum = service.getNextOrderNumber();
        assertEquals(orderNum, order.getOrderNumber()+1, 
                            "The next order number must be the order number "
                                    + "of last order inserted + 1");
        
    }
    
    //update an order
    @Test
    public void testUpdateAndSelectOrder() 
            throws FileNotFoundException, FlooringMasteryPersistenceException {
        String dateString = "2040-12-20";
        Order oldOrder = new Order(5                       , "Doctor Who"
                                , "WA"                     , new BigDecimal("9.25")
                                , "Wood"                   , new BigDecimal("243.00")
                                , new BigDecimal("5.15")   , new BigDecimal("4.75")
                                , new BigDecimal("1251.45"), new BigDecimal("1154.25")
                                , new BigDecimal("216.51") , new BigDecimal("2622.21"));
       service.placeOrder(oldOrder, dateString);
       Order newOrder = new Order( 5                       , "Engineer Who"
                                , "WA"                     , new BigDecimal("9.25")
                                , "Wood"                   , new BigDecimal("243.00")
                                , new BigDecimal("5.15")   , new BigDecimal("4.75")
                                , new BigDecimal("1251.45"), new BigDecimal("1154.25")
                                , new BigDecimal("216.51") , new BigDecimal("2622.21"));
       service.updateOrder(oldOrder, newOrder, dateString);
       List<Order> orderList = service.readOrdersFromFile(dateString);
       assertFalse(orderList.contains(oldOrder), "The order list must not contain old order");
       assertTrue(orderList.contains(newOrder), "The order list must contain new order");
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
