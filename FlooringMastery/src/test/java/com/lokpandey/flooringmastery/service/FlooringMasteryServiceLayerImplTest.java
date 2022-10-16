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
    public void testSomeMethod() {
    }
    
}
