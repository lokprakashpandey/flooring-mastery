/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 06, 2022
 * purpose: UserIO interface from TSG
 */

package com.lokpandey.flooringmastery.view;

/**
 *
 * @author lokpandey
 */
public interface UserIO {
    
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
    
}
