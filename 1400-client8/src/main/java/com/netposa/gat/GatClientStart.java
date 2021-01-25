package com.netposa.gat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author netposa
 */
@SpringBootApplication
@ComponentScan(value = "com.netposa")
@MapperScan("com.**.mapper")
public class GatClientStart {
    public static void main(String[] args) {
    	  SpringApplication springApplication = new SpringApplication(GatClientStart.class);
  	    
          springApplication.addListeners(new AppStartListener());
          
          springApplication.run(args);
    }
}
