package com.promineotech.jeep;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.promineotech.ComponentScanMarker;

//@SpringBootApplication
//Spring will find the class with the @Componentscan annotation.
//will scan in package and sub-packages.
//Can use @Componentscan to tell system to scan in other areas.

//@SpringBootApplication(scanBasePackages = {"com.promineotech"})
//Or, we can create a scanner class as below:

@SpringBootApplication(scanBasePackageClasses = { ComponentScanMarker.class})



public class JeepSales {

  public static void main(String[] args) {
    
    //Starts Spring boot, pass JeepSales.class, finds spring boot annotations
    SpringApplication.run(JeepSales.class, args);
  }
}
