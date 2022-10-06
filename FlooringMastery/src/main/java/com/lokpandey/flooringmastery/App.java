/*
 * @author Lok Prakash Pandey
 * email: lokprakashpandey@gmail.com
 * date: Oct 6, 2022
 * purpose: Create App App
 */

package com.lokpandey.flooringmastery;

import com.lokpandey.flooringmastery.controller.FlooringMasteryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lokpandey
 */
public class App {

    public static void main(String[] args) {
        
        ApplicationContext ctx = 
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
                    ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
}
