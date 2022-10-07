/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Order class for DTO/model
 */

package com.lokpandey.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class Order {
    
    private final Integer orderNumber;
    private final String customerName;
    private final String state;
    private final BigDecimal taxRate;
    private final String productType;
    private final BigDecimal area;
    private final BigDecimal costPerSquareFoot;
    private final BigDecimal laborCostPerSquareFoot;
    private final BigDecimal materialCost;
    private final BigDecimal laborCost;
    private final BigDecimal tax;
    private final BigDecimal total;

    public Order(Integer orderNumber, String customerName, String state, BigDecimal taxRate, 
                 String productType, BigDecimal area, BigDecimal costPerSquareFoot, 
                 BigDecimal laborCostPerSquareFoot) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        
        // calculations after above information is provided for creating object 
        this.materialCost = (area.multiply(costPerSquareFoot)).setScale(2, RoundingMode.HALF_UP);
        this.laborCost = (area.multiply(laborCostPerSquareFoot)).setScale(2, RoundingMode.HALF_UP);
        this.tax = ((materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal("100")))).setScale(2, RoundingMode.HALF_UP);
        this.total = (materialCost.add(laborCost).add(tax)).setScale(2, RoundingMode.HALF_UP);
        
    }

    public Order(Integer orderNumber, String customerName, String state, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.orderNumber);
        hash = 79 * hash + Objects.hashCode(this.customerName);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + Objects.hashCode(this.taxRate);
        hash = 79 * hash + Objects.hashCode(this.productType);
        hash = 79 * hash + Objects.hashCode(this.area);
        hash = 79 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 79 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 79 * hash + Objects.hashCode(this.materialCost);
        hash = 79 * hash + Objects.hashCode(this.laborCost);
        hash = 79 * hash + Objects.hashCode(this.tax);
        hash = 79 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }

    
    
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }  

}
