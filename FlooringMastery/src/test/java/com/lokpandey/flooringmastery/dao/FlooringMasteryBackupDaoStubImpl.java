/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation of back up dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FlooringMasteryBackupDaoStubImpl implements FlooringMasteryBackupDao {

    @Override
    public List<Order> exportAllOrders() throws FlooringMasteryPersistenceException {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order( 1                        , "Ada Lovelace"
                               , "CA"                     , new BigDecimal("25.00")
                               , "Tile"                   , new BigDecimal("249.00")
                               , new BigDecimal("3.50")   , new BigDecimal("4.15")
                               , new BigDecimal("871.50") , new BigDecimal("1033.35")
                               , new BigDecimal("476.21") , new BigDecimal("2381.06") ) );
        orderList.add(new Order( 2                        , "Doctor Who"
                               , "WA"                     , new BigDecimal("9.25")
                               , "Wood"                   , new BigDecimal("243.00")
                               , new BigDecimal("5.15")   , new BigDecimal("4.75")
                               , new BigDecimal("1251.45"), new BigDecimal("1154.25")
                               , new BigDecimal("216.51") , new BigDecimal("2622.21") ) );
        orderList.add(new Order( 3                        , "Albert Einstein"
                               , "KY"                     , new BigDecimal("6.00")
                               , "Carpet"                 , new BigDecimal("217.00")
                               , new BigDecimal("2.25")   , new BigDecimal("2.10")
                               , new BigDecimal("488.25") , new BigDecimal("455.70")
                               , new BigDecimal("56.64")  , new BigDecimal("1000.59") ) );
        return orderList;
    }
}
