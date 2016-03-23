package org.naresh.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is spring boot application where it will load all the configuration values 
 * and resolve the dependency injection
 *
 */
@ComponentScan("org.naresh")
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
           SpringApplication.run(App.class, args);
		
    }
}
