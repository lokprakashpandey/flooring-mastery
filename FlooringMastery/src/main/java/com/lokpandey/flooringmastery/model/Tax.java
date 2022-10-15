/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: Tax class as DTO
 */

package com.lokpandey.flooringmastery.model;

import java.math.BigDecimal;
import java.util.Objects;


public class Tax {

    private final String stateAbbreviation;
    private final String stateName;
    private final BigDecimal taxRate;

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 37 * hash + Objects.hashCode(this.stateName);
        hash = 37 * hash + Objects.hashCode(this.taxRate);
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        return Objects.equals(this.taxRate, other.taxRate);
    }
    
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
}
