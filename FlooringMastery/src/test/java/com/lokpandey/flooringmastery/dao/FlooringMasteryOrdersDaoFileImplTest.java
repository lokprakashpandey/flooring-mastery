/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date:
 * purpose:
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lokpandey
 */
public class FlooringMasteryOrdersDaoFileImplTest {
    
    FlooringMasteryOrdersDao testOrdersDao;
    
    public FlooringMasteryOrdersDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() {
        //testDao = new FlooringMasteryOrdersDaoFileImpl("TestOrders/");
        ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        testOrdersDao = ctx.getBean("ordersDao", FlooringMasteryOrdersDaoFileImpl.class);
    }

    
    @Test
    public void testSelectAllFromOrders() throws FileNotFoundException {
        //Test for successful read
        Order order = new Order(1, "Ada Lovelace", "CA", new BigDecimal("25.00")
                                , "Tile", new BigDecimal("249.00"), new BigDecimal("3.50")
                                , new BigDecimal("4.15"), new BigDecimal("871.50")
                                , new BigDecimal("1033.35"), new BigDecimal("476.21")
                                , new BigDecimal("2381.06"));
        String testFileName = "Orders_06012013.txt";
        List<Order> listFromFile = testOrdersDao.selectAllFromOrders(testFileName);
        assertEquals(listFromFile.size(), 1, "The list size should be 1");
        //Must return true since equals and hashcode is implemented in Order class
        assertTrue(listFromFile.contains(order), "The List must contain the order");
        
        //Test for unsuccessful read when no file exists
        testFileName = "Orders_06012014.txt";
        try {
            //since the file does not exist, exception must be raised
            testOrdersDao.selectAllFromOrders(testFileName);
            fail("Exception must have been raised");
        } catch(FileNotFoundException fnfe) {
            
        }
    }
    
}
