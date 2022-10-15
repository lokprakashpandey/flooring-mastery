/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Testing for file export
 */
package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
public class FlooringMasteryBackupDaoFileImplTest {
    
    FlooringMasteryBackupDao testBackupDao;
    
    public FlooringMasteryBackupDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() {
        ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        testBackupDao = ctx.getBean("backupDao", FlooringMasteryBackupDaoFileImpl.class);
    }
    
    @AfterEach
    public void tearDown() throws IOException {
        Files.delete(Paths.get("Backup/DataExport.txt"));
    }

    @Test
    public void testExportAllOrders() throws FlooringMasteryPersistenceException {
        //ARRANGE
        Order order = new Order(1, "Ada Lovelace" 
                            , "CA", new BigDecimal("25.00")
                            , "Tile", new BigDecimal("249.00")
                            , new BigDecimal("3.50"), new BigDecimal("4.15")
                            , new BigDecimal("871.50"), new BigDecimal("1033.35")
                            , new BigDecimal("476.21"), new BigDecimal("2381.06"));
        
        //ACT
        List<Order> exportedList = testBackupDao.exportAllOrders();
        
        //ASSERT
        assertTrue(exportedList.contains(order), 
                    "The exported list must contain the order");
    } 
}
