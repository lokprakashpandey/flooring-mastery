/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Testing class for FlooringMasteryProductsDaoFileImpl
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Product;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FlooringMasteryProductsDaoFileImplTest {
    
    FlooringMasteryProductsDao testProductsDao;
    
    public FlooringMasteryProductsDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() {
        ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        testProductsDao = ctx.getBean("productsDao", FlooringMasteryProductsDaoFileImpl.class);
    }

    @Test
    public void testSelectAllFromProducts() throws FileNotFoundException {
        //ARRANGE
        List<Product> list = new ArrayList<>();
        Product p1 = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10")); 
        Product p2 = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        Product p3 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15")); 
        Product p4 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        
        //ACT
        List<Product> productListFromFile = testProductsDao.selectAllFromProducts();
        
        //ASSERT
        assertEquals(productListFromFile.size(), list.size(), "The list size should be equal");
        
        //Since hashCode() and equals() method are implemented, 
        //so the contains() method should return true
        assertTrue(productListFromFile.contains(p1), "The list must contain the product");
        assertTrue(productListFromFile.contains(p2), "The list must contain the product");
        assertTrue(productListFromFile.contains(p3), "The list must contain the product");
        assertTrue(productListFromFile.contains(p4), "The list must contain the product");
    }
}
