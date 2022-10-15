/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Test class for Taxes dao
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Tax;
import com.lokpandey.flooringmastery.service.CannotSellException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
public class FlooringMasteryTaxesDaoFileImplTest {
    
    FlooringMasteryTaxesDao testTaxesDao;
    
    public FlooringMasteryTaxesDaoFileImplTest() {
        
    }
    
    @BeforeEach
    public void setUp() {
    ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
    testTaxesDao = ctx.getBean("taxesDao", FlooringMasteryTaxesDaoFileImpl.class);
    }
    
    @Test
    public void testSelectFromTax() throws FileNotFoundException, CannotSellException {
        Tax tax = new Tax("CA", "California", new BigDecimal("25.00"));
        
        Tax taxFromFile = testTaxesDao.selectFromTax("CA");
        
        assertEquals(tax, taxFromFile, "Both objects muust be equal");
        
        try {
            testTaxesDao.selectFromTax("YZ");
            fail("Exception must be thrown");
        } catch(CannotSellException cse) { }
    }
}
