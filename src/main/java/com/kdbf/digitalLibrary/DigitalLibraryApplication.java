package com.kdbf.digitalLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DigitalLibraryApplication {

  public static void main(String[] args) {
    SpringApplication.run(DigitalLibraryApplication.class, args);
  }

}
