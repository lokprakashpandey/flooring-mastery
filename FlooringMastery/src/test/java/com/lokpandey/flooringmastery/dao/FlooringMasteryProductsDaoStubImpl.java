/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 15, 2022
 * purpose: Stub implementation for products dao
 */

package com.lokpandey.flooringmastery.dao;

import com.lokpandey.flooringmastery.model.Product;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FlooringMasteryProductsDaoStubImpl implements FlooringMasteryProductsDao {

    @Override
    public List<Product> selectAllFromProducts() throws FileNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10")));
        productList.add(new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10")));
        productList.add(new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15")));
        productList.add(new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75")));
        return productList;
    }

}
