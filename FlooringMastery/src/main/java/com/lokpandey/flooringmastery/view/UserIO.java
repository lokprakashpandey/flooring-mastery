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
    
    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);
    
    
}
