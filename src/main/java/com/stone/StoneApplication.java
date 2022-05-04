package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.stone","com.stone.web"})
public class StoneApplication {


    public static void main(String[] args) {

        SpringApplication.run(StoneApplication.class, args);
    }

}
